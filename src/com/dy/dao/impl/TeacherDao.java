package com.dy.dao.impl;

import com.dy.entity.GradeList;
import com.dy.entity.Group;
import com.dy.entity.Student;
import com.dy.entity.Teacher;
import com.dy.utils.DBUtils;
import com.sun.corba.se.impl.orb.ParserTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/17 22:41
 * @description:
 */
public class TeacherDao {
    //通过sno和password查询（用于登录）
    public Teacher queryBySnoPas(int sno,String password){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select tsno,tname,password,status,group_id,ischeck from t_user where tsno=? and password=?";
        Teacher teacher=new Teacher();
        try {
            conn= DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,sno);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            while (rs.next()){
                teacher.setTno(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setStatus(rs.getString(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                teacher.setGroup(group);
                teacher.setIsChecked(rs.getString(6));
            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //通过id查询信息
    public Teacher queryById(int id){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from t_user where id=?";
        Teacher teacher=new Teacher();
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while (rs.next()){
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTno(rs.getInt(3));
                teacher.setSex(rs.getString(4));
                Group group=new Group();
                GroupDao groupDao=new GroupDao();
                group=groupDao.queryById(rs.getInt(5));
                teacher.setGroup(group);
                teacher.setStatus(rs.getString(6));

            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacher;
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
        String sql="Select count(tname) from t_user where tname like ?";
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
    public List<Teacher> queryByName(String keyword, int currentPage, int PageSize){

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select * from t_user where tname like ? order by id limit ?,?";

        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%"+keyword+"%");
            pstmt.setInt(2,(currentPage-1)*PageSize);
            pstmt.setInt(3,PageSize);
            rs=pstmt.executeQuery();
            List<Teacher> teacherList=new ArrayList<Teacher>();
            while (rs.next()){

                Teacher teacher=new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTno(rs.getInt(3));
                teacher.setSex(rs.getString(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                teacher.setGroup(group);
                teacher.setStatus(rs.getString(6));
                teacherList.add(teacher);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacherList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //通过id查询修改教师信息
    public void updateTeacher(Teacher teacher){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="update t_user set tname=?,tsex=?,group_id=?,status=? where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacher.getName());
            pstmt.setString(2,teacher.getSex());
            pstmt.setInt(3,teacher.getGroup().getId());
            pstmt.setString(4,teacher.getStatus());
            pstmt.setInt(5,teacher.getId());
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除教师
    public void deleteTeacher(Teacher teacher){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="delete from t_user where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,teacher.getId());
            int isok=pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //添加教师
    public void addTeacher(Teacher teacher){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="insert into t_user(tsno,tname,tsex,group_id,status) values(?,?,?,?,?)";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,teacher.getTno());
            pstmt.setString(2,teacher.getName());
            pstmt.setString(3,teacher.getSex());
            pstmt.setInt(4,teacher.getGroup().getId());
            pstmt.setString(5,teacher.getStatus());
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //根据小组id查询教师信息
    public List<Teacher> queryByGroupId(int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="SELECT * from t_user where group_id=? ORDER BY id;";
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            rs=pstmt.executeQuery();
            List<Teacher> teacherList=new ArrayList<Teacher>();
            while (rs.next()){
                Teacher teacher=new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTno(rs.getInt(3));
                teacher.setSex(rs.getString(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                teacher.setGroup(group);
                teacher.setStatus(rs.getString(6));
                teacher.setIsChecked(rs.getString(8));
                teacherList.add(teacher);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacherList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询没有小组的教师
    public List<Teacher> queryIsGrouped(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="SELECT * from t_user WHERE group_id=-1";
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            List<Teacher> teacherList=new ArrayList<Teacher>();
            while (rs.next()){
                Teacher teacher=new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTno(rs.getInt(3));
                teacher.setSex(rs.getString(4));
                GroupDao groupDao=new GroupDao();
                Group group=groupDao.queryById(rs.getInt(5));
                teacher.setGroup(group);
                teacher.setStatus(rs.getString(6));
                teacherList.add(teacher);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacherList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //修改对应id教师的小组
    public void updateTeacherGroup(int id,int groupid,String status){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="update t_user set group_id=?,status=? where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            pstmt.setString(2,status);
            pstmt.setInt(3,id);
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查找某组的组长
    public Teacher queryGroupLeaderById(int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from t_user where group_id=? and status='groupleader'";
        Teacher teacher=new Teacher();
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            rs=pstmt.executeQuery();
            while (rs.next()){
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTno(rs.getInt(3));
                teacher.setSex(rs.getString(4));
                Group group=new Group();
                teacher.setGroup(group);
                teacher.setStatus(rs.getString(6));

            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //从小组中移除
    public void removeTeacher(Teacher teacher){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="update t_user set group_id=-1 where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,teacher.getId());
            int isok= pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //通过tno查询教师信息
    public Teacher queryByTno(int tno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="Select * from t_user where tsno=?";
        Teacher teacher=new Teacher();
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,tno);
            rs=pstmt.executeQuery();
            while (rs.next()){
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setTno(rs.getInt(3));
                GroupDao groupDao=new GroupDao();
                Group group=new Group();
                group=groupDao.queryById(rs.getInt(5));
                teacher.setGroup(group);
                teacher.setIsChecked(rs.getString(8));
            }
            conn.close();
            pstmt.close();
            rs.close();
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //考勤
    public void check(int tno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="update t_user set ischeck=? where tsno=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"是");
            pstmt.setInt(2,tno);
            int isok=pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
