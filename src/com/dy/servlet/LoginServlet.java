package com.dy.servlet; /**
 * @author: zjd-DY
 * @date: 2021/6/17 22:44
 * @description:
 */

import com.dy.dao.impl.GroupDao;
import com.dy.dao.impl.StudentDao;
import com.dy.dao.impl.TeacherDao;
import com.dy.entity.GradeList;
import com.dy.entity.Group;
import com.dy.entity.Student;
import com.dy.entity.Teacher;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        switch (method){
            //登录
            case "login":
                String status=request.getParameter("status");
                if("student".equals(status)){
                    StudentDao studentDao=new StudentDao();
                    String username=request.getParameter("username-login");
                    String password=request.getParameter("password-login");
                    Student student=studentDao.queryBySnoPas(Integer.parseInt(username),password);
                    if (studentDao.queryBySnoPas(Integer.parseInt(username),password).getSno()!=0){
                        response.sendRedirect("index.jsp");
                        HttpSession session=request.getSession(true);
                        session.setAttribute("user",student);
                        session.setAttribute("status","student");
                    }
                    else {
                        response.sendRedirect("login_register.jsp?failreason=fail");
                    }
                }else if("teacher".equals(status)){
                    TeacherDao teacherDao=new TeacherDao();
                    String username=request.getParameter("username-login");
                    String password=request.getParameter("password-login");
                    Teacher teacher=teacherDao.queryBySnoPas(Integer.parseInt(username),password);
                    if ((teacherDao.queryBySnoPas(Integer.parseInt(username),password).getTno())!=0){
                        HttpSession session=request.getSession(true);
                        session.setAttribute("user",teacher);
                        session.setAttribute("status",teacher.getStatus());
                        //将所有的小组信息放到session域中
                        GroupDao groupDao=new GroupDao();
                        List<Group> groupList=groupDao.queryAllGroup();
                        session.setAttribute("groupList",groupList);
                        response.sendRedirect("index.jsp");
                    }
                    else {
                        response.sendRedirect("login_register.jsp?failreason=fail");
                    }
                }else {

                }
                break;
            case "logout":
                HttpSession session=request.getSession();
                session.setAttribute("user", null);
                request.getRequestDispatcher("login_register.jsp").forward(request, response);
                break;
        }
    }
}
