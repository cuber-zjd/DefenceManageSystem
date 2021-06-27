package com.dy.servlet;

import com.dy.dao.impl.GradeDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zjd-DY
 * @date: 2021/6/20 18:39
 * @description:
 */
@WebServlet(name = "GradeServlet" ,value = "/GradeServlet")
public class GradeServlet extends BaseServlet{
    //查询分段成绩人数
    protected void queryCounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int[] counts=new int[5];
        GradeDao gradeDao=new GradeDao();
        counts[0]=gradeDao.getGradeSub(0,39);
        counts[1]=gradeDao.getGradeSub(40,59);
        counts[2]=gradeDao.getGradeSub(60,79);
        counts[3]=gradeDao.getGradeSub(80,89);
        counts[4]=gradeDao.getGradeSub(90,100);
        Gson gson=new Gson();
        String countsJsonString=gson.toJson(counts);
        resp.getWriter().write(countsJsonString);

    }
}
