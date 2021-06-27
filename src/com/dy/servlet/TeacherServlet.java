package com.dy.servlet; /**
 * @author: zjd-DY
 * @date: 2021/6/17 22:41
 * @description:
 */

import com.dy.dao.impl.GroupDao;
import com.dy.dao.impl.StudentDao;
import com.dy.dao.impl.TeacherDao;
import com.dy.entity.Group;
import com.dy.entity.Student;
import com.dy.entity.Teacher;
import com.dy.utils.PageBeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TeacherServlet", value = "/TeacherServlet")
public class TeacherServlet extends BaseServlet {
    //通过姓名查询教师所有信息
    protected void searchAllTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String kw=req.getParameter("kw");
        req.setAttribute("kw",kw);
        StudentDao studentDao=new StudentDao();
        TeacherDao teacherDao=new TeacherDao();
        if (null==kw){
            kw="";
        }
        //设置当前页数（抛出异常，小于1时，设置当前页数为1）
        int currentPage=1;
        try {
            currentPage=Integer.parseInt(req.getParameter("curPage"));

            if (currentPage<=0){
                currentPage=1;
            }
        }catch (Exception e){
            currentPage=1;

        }
        //设置每页多少数据
        int pageSize=10;
        //获取一共有多少数据
        int totalData=0;
        try {
            totalData=teacherDao.getTotalNumByName(kw);

        }catch (Exception e){
            e.printStackTrace();
        }
        //根据总数据量和每页的数据量设置数据总页数
        int totalPage=(int)Math.ceil((double) totalData/pageSize);
        if (currentPage>totalPage){
            currentPage=totalPage;
        }
        if (totalData==0){
            currentPage=1;
        }
        //获取学生信息
        List<Teacher> teacherList=null;
        teacherList=teacherDao.queryByName(kw,currentPage,pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg=new PageBeanUtils(currentPage,pageSize,totalData);
        pg.setPageData(teacherList);
        req.setAttribute("pg",pg);
        req.getRequestDispatcher("/Pages/managerPage/teachermanage.jsp").forward(req,resp);
        /*resp.sendRedirect("Page/managerPage/studentmanage.jsp");*/
    }
    //修改教师信息
    protected void updateTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("edit-id"));
        String name=req.getParameter("edit-name");
        String sex=req.getParameter("edit-sex");
        int groupid=Integer.parseInt(req.getParameter("edit-group"));
        String status=req.getParameter("edit-status");
        Teacher teacher=new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setSex(sex);
        Group group=new Group();
        group.setId(groupid);
        teacher.setStatus(status);
        teacher.setGroup(group);
        TeacherDao teacherDao=new TeacherDao();
        teacherDao.updateTeacher(teacher);
        req.getRequestDispatcher("TeacherServlet?action=searchAllTeacher&kw=").forward(req,resp);
    }
    //删除教师
    protected void deleteTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        Teacher teacher=new Teacher();
        teacher.setId(id);
        TeacherDao teacherDao=new TeacherDao();
        teacherDao.deleteTeacher(teacher);
        req.getRequestDispatcher("TeacherServlet?action=searchAllTeacher&kw=").forward(req,resp);
    }
    //添加教师
    protected void addTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tno=Integer.parseInt(req.getParameter("add-tno"));
        String name=req.getParameter("add-name");
        String sex=req.getParameter("add-sex");
        int groupid=Integer.parseInt(req.getParameter("add-group"));
        String status=req.getParameter("add-status");
        Teacher teacher=new Teacher();
        teacher.setTno(tno);
        teacher.setName(name);
        teacher.setSex(sex);
        Group group=new Group();
        group.setId(groupid);
        teacher.setGroup(group);
        teacher.setStatus(status);
        TeacherDao teacherDao=new TeacherDao();
        teacherDao.addTeacher(teacher);
        req.getRequestDispatcher("TeacherServlet?action=searchAllTeacher&kw=").forward(req,resp);
    }
    //考勤
    protected void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tno=Integer.parseInt(req.getParameter("tno"));
        TeacherDao teacherDao=new TeacherDao();
        teacherDao.check(tno);
        req.getRequestDispatcher("TeacherServlet?action=queryCheckTimeByGroupId").forward(req,resp);
    }
    //查询 小组考勤时间范围&本小组是否设置考勤
    protected void queryCheckTimeByGroupId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Group group = new Group();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        /*
         * 小组考勤时间范围*/
        GroupDao groupDao = new GroupDao();
        group = groupDao.queryById(teacher.getGroup().getId());
        group.setCheck(groupDao.queryCheckTimeByGroupId(teacher.getGroup().getId()));
        group.setId(teacher.getGroup().getId());
        //处理得到的考勤时间
        Calendar time = Calendar.getInstance();
        Date datetemp = group.getDefencetime();
        Date starttime = new Date(datetemp.getTime() - group.getCheck().getStart() * 60 * 1000);
        Date endtime = new Date(datetemp.getTime() + group.getCheck().getEnd() * 60 * 1000);
        /*
        * 小组是否设置考勤*/
        if (group.getCheck().getStart()!=0){
            req.setAttribute("isSetChecked",true);
        }else {
            req.setAttribute("isSetChecked",false);
        }
        /*
        * 登录教师是否考勤*/
        TeacherDao teacherDao=new TeacherDao();
        Teacher teacher2=teacherDao.queryByTno(teacher.getTno());
        session.setAttribute("user",teacher2);

        req.setAttribute("starttime", starttime);
        req.setAttribute("endtime", endtime);
        req.getRequestDispatcher("/Pages/teacherPage/checkPage.jsp").forward(req, resp);
    }
}
