package com.dy.dao.impl;

import com.dy.entity.*;
import com.dy.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/19 10:52
 * @description:
 */
public class GroupDao {
    //根据id查询小组
    public Group queryById(int groupid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * from `group` WHERE id=?";
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupid);
            Group group = new Group();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                group.setId(groupid);
                group.setName(rs.getString(2));
                Timestamp time =rs.getTimestamp(3);
                Date date= new Date(time.getTime());
                group.setDefencetime(date);
                TeacherDao teacherDao = new TeacherDao();
                Teacher teacher = teacherDao.queryGroupLeaderById(groupid);
                group.setLeader(teacher);

            }
            conn.close();
            pstmt.close();
            rs.close();
            return group;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询所有小组
    public List<Group> queryAllGroup(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * from `group`";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            List<Group> groupList=new ArrayList<Group>();

            while (rs.next()){
                Group group=new Group();
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                Date date=rs.getTime(3);
                group.setDefencetime(date);
                TeacherDao teacherDao=new TeacherDao();
                Teacher teacher=teacherDao.queryGroupLeaderById(rs.getInt(1));
                group.setLeader(teacher);
                groupList.add(group);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return groupList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询数据总数
    public int getTotalNumByName(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="SELECT COUNT(gname) from `group`";
        Group group=new Group();
        try {
            int totalData = 0;
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
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
    //查询得到所有数据(分页)
    public List<Group> queryAllGroup(int currentPage,int PageSize){

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="SELECT * from `group` ORDER BY id limit ?,?";

        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,(currentPage-1)*PageSize);
            pstmt.setInt(2,PageSize);
            rs=pstmt.executeQuery();
            List<Group> groupList=new ArrayList<Group>();
            while (rs.next()){
                Group group=new Group();
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                Timestamp time =rs.getTimestamp(3);
                Date date= new Date(time.getTime());
                group.setDefencetime(date);
                TeacherDao teacherDao=new TeacherDao();
                Teacher teacher=teacherDao.queryGroupLeaderById(rs.getInt(1));
                group.setLeader(teacher);
                groupList.add(group);
            }
            conn.close();
            pstmt.close();
            rs.close();
            return groupList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //根据id删除小组
    public void deleteGroup(Group group){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="delete from `group` where id=?";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,group.getId());
            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //添加小组
    public void addGroup(Group group){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="INSERT INTO `group`(gname,defencetime,leaderid) values(?,?,?)";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,group.getName());
            Timestamp time=new Timestamp(group.getDefencetime().getTime());
            pstmt.setTimestamp(2,time);
            pstmt.setInt(3,group.getLeader().getId());

            int isok=pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //根据组名查询小组
    public Group queryByName(String gname){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from `group` where gname=?";

        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,gname);
            rs=pstmt.executeQuery();
            Group group =new Group();
            while (rs.next()){
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                Teacher teacher=new Teacher();
                teacher.setId(rs.getInt(4));
                group.setLeader(teacher);

            }
            conn.close();
            pstmt.close();
            rs.close();
            return group;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //为小组添加考勤
    public void addCheck(Check check,int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql="insert into checkwork(groupid,start,end) values (?,?,?)";
        try {
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            pstmt.setInt(2,check.getStart());
            pstmt.setInt(3,check.getEnd());
            int isok=pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //查询小组考勤时间
    public Check queryCheckTimeByGroupId(int groupid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select start,end from checkwork where groupid=?";
        try {
            Check check=new Check();
            conn=DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,groupid);
            rs=pstmt.executeQuery();
            while (rs.next()){
                check.setStart(rs.getInt(1));
                check.setEnd(rs.getInt(2));
            }
            conn.close();
            pstmt.close();
            rs.close();
            return check;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
