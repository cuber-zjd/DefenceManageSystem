package com.dy.dao.impl;

import com.dy.entity.GradeList;
import com.dy.entity.Student;
import com.dy.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: zjd-DY
 * @date: 2021/6/18 23:28
 * @description:
 */
public class GradeDao {
    //根据学号获取个人成绩
    public GradeList getGrade(int sno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="SELECT AVG(context),AVG(innovate),AVG(defence),AVG(time),AVG(grade) from defence_result WHERE sno=? GROUP BY sno ";
        Student student=new Student();
        try {
            conn= DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,sno);
            GradeList grade=new GradeList();
            rs=pstmt.executeQuery();
            while (rs.next()){
                grade.setContext(rs.getInt(1));
                grade.setInnovate(rs.getInt(2));
                grade.setDefence(rs.getInt(3));
                grade.setTime(rs.getInt(4));
                grade.setGrade(rs.getInt(5));

            }
            conn.close();
            pstmt.close();
            rs.close();
            return grade;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询分段成绩
    public int getGradeSub(int begin,int end){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="SELECT COUNT(sno) from (SELECT sno,AVG(grade) avggrade from defence_result group by sno having AVG(grade)>= ? and avg(grade)<=?) a";
        Student student=new Student();
        int count=0;
        try {
            conn= DBUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,begin);
            pstmt.setInt(2,end);
            rs=pstmt.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);

            }
            conn.close();
            pstmt.close();
            rs.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
