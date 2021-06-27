package com.dy.dao.impl;

import com.dy.entity.GradeList;
import com.dy.entity.Group;
import com.dy.entity.Student;
import com.dy.entity.Teacher;
import com.dy.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/17 21:31
 * @description:
 */
public class StudentDao{
    //通过学号密码查询相关信息（用于登录）
    public Student queryBySnoPas(int sno,String password){

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select sno,sname,password from s_user where sno=? and password=?";
        Student student=new Student();
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,sno);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            while (rs.next()){
                student.setSno(rs.getInt(1));
                student.setName(rs.getString(2));

            }
            conn.close();
            pstmt.close();
            rs.close();
            return student;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //添加论文的url地址
    public boolean addUrl(int sno,String url){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="UPDATE s_user SET paperurl=? where sno=?";
        Student student=new Student();
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,url);
            pstmt.setInt(2,sno);
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //通过学号查询信息
    public Student queryBySno(int sno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select * from s_user where sno=?";
        Student student=new Student();
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,sno);
            rs=pstmt.executeQuery();
            while (rs.next()){
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setSno(sno);
                Group group=new Group();
                group.setId(rs.getInt(5));
                student.getGroup();
                student.setPaperUrl(rs.getString(7));

            }
            conn.close();
            pstmt.close();
            rs.close();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //通过对姓名模糊查询得到数据总数
    public int getTotalNumByName(String keyword){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select count(sname) from s_user where sname like ?";
        Student student=new Student();
        try {
            int totalData = 0;
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%"+keyword+"%");
            rs=pstmt.executeQuery();
            while (rs.next()){
                totalData = rs.getInt(1);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return totalData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //通过对姓名模糊查询得到对应数据(分页)
    public List<Student> queryByName(String keyword,int currentPage,int PageSize){

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select * from s_user where sname like ? order by id limit ?,?";

        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%"+keyword+"%");
            pstmt.setInt(2,(currentPage-1)*PageSize);
            pstmt.setInt(3,PageSize);
            rs=pstmt.executeQuery();
            List<Student> studentList=new ArrayList<Student>();
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setSno(rs.getInt(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                student.setGroup(group);
                GradeDao gradeDao=new GradeDao();
                GradeList grade=new GradeList();
                grade=gradeDao.getGrade(rs.getInt(4));
                student.setGrade(grade);
                studentList.add(student);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return studentList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //通过id查询修改学生信息
    public void updateStudent(Student student){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="update s_user set sname=?,ssex=?,groupid=? where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,student.getName());
            pstmt.setString(2,student.getSex());
            pstmt.setInt(3,student.getGroup().getId());
            pstmt.setInt(4,student.getId());
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //添加学生
    public void addStudent(Student student){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="insert into s_user(sno,sname,ssex,groupid) values(?,?,?,?)";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,student.getSno());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setInt(4,student.getGroup().getId());
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除学生
    public void deleteStudent(Student student){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="delete from s_user where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,student.getId());
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //根据小组id查询学生信息
    public List<Student> queryByGroupId(int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="SELECT * from s_user where groupid=? ORDER BY id;";
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            rs=pstmt.executeQuery();
            List<Student> studentList=new ArrayList<Student>();
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setSno(rs.getInt(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                student.setGroup(group);
                GradeDao gradeDao=new GradeDao();
                GradeList grade=new GradeList();
                grade=gradeDao.getGrade(rs.getInt(4));
                student.setGrade(grade);
                student.setPaperUrl(rs.getString(7));
                studentList.add(student);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //从小组中移除
    public void removeStudent(Student student){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="update s_user set groupid=-1 where id=?";
        try {
            conn= DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,student.getId());
            int isok= pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //查询没有小组的学生
    public List<Student> queryIsGrouped(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="SELECT * from s_user WHERE groupid=-1";
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            List<Student> studentList=new ArrayList<Student>();
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setSno(rs.getInt(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                student.setGroup(group);

                studentList.add(student);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //修改对应id学生的小组
    public void updateStudentGroup(int id,int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="update s_user set groupid=? where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            pstmt.setInt(2,id);
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //通过对组号查询得到数据总数
    public int getTotalNumByGroupId(int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select count(sname) from s_user where groupid=?";
        Student student=new Student();
        try {
            int totalData = 0;
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            rs=pstmt.executeQuery();
            while (rs.next()){
                totalData = rs.getInt(1);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return totalData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //通过对小组id查询得到对应数据(分页)
    public List<Student> queryByGroupId(int groupid,int currentPage,int PageSize){

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select * from s_user where groupid=? order by id limit ?,?";

        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            pstmt.setInt(2,(currentPage-1)*PageSize);
            pstmt.setInt(3,PageSize);
            rs=pstmt.executeQuery();
            List<Student> studentList=new ArrayList<Student>();
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setSno(rs.getInt(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                student.setGroup(group);
                GradeDao gradeDao=new GradeDao();
                GradeList grade=new GradeList();
                grade=gradeDao.getGrade(rs.getInt(4));
                student.setGrade(grade);
                studentList.add(student);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return studentList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //评分
    public void mark(int sno,GradeList grade,int tno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="INSERT into defence_result(sno,context,innovate,defence,time,grade,tno) VALUES(?,?,?,?,?,?,?);";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,sno);
            pstmt.setInt(2,grade.getContext());
            pstmt.setInt(3,grade.getInnovate());
            pstmt.setInt(4,grade.getDefence());
            pstmt.setInt(5,grade.getTime());
            pstmt.setInt(6,grade.getGrade());
            pstmt.setInt(7,tno);
            int isok=pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //根据小组id查询学生信息(不包含登录教师已经打过分数的学生)
    public List<Student> queryByGroupId(int groupid,int tno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="SELECT * from s_user where groupid=? and sno NOT IN (SELECT sno from defence_result WHERE tno=? )ORDER BY id ";
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            pstmt.setInt(2,tno);
            rs=pstmt.executeQuery();
            List<Student> studentList=new ArrayList<Student>();
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setSno(rs.getInt(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                student.setGroup(group);
                GradeDao gradeDao=new GradeDao();
                GradeList grade=new GradeList();
                grade=gradeDao.getGrade(rs.getInt(4));
                student.setGrade(grade);
                studentList.add(student);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
