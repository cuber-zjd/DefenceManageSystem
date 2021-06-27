package com.dy.servlet;

import com.dy.dao.impl.GroupDao;
import com.dy.dao.impl.StudentDao;
import com.dy.dao.impl.TeacherDao;
import com.dy.entity.Check;
import com.dy.entity.Group;
import com.dy.entity.Student;
import com.dy.entity.Teacher;
import com.dy.utils.PageBeanUtils;
import com.sun.corba.se.impl.ior.iiop.IIOPProfileTemplateImpl;
import org.apache.taglibs.standard.tag.common.core.RedirectSupport;
import sun.security.jgss.GSSHeader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: zjd-DY
 * @date: 2021/6/20 10:09
 * @description:
 */
@WebServlet(name = "GroupServlet", value = "/GroupServlet")
public class GroupServlet extends BaseServlet {
    //通过小组名查询小组所有信息
    protected void searchAllGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GroupDao groupDao = new GroupDao();
        //设置当前页数（抛出异常，小于1时，设置当前页数为1）
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(req.getParameter("curPage"));

            if (currentPage <= 0) {
                currentPage = 1;
            }
        } catch (Exception e) {
            currentPage = 1;

        }
        //设置每页多少数据
        int pageSize = 10;
        //获取一共有多少数据
        int totalData = 0;
        try {
            totalData = groupDao.getTotalNumByName();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据总数据量和每页的数据量设置数据总页数
        int totalPage = (int) Math.ceil((double) totalData / pageSize);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (totalData == 0) {
            currentPage = 1;
        }
        //获取学生信息
        List<Group> groupList = null;
        groupList = groupDao.queryAllGroup(currentPage, pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg = new PageBeanUtils(currentPage, pageSize, totalData);
        pg.setPageData(groupList);
        req.setAttribute("pg", pg);
        req.getRequestDispatcher("/Pages/managerPage/groupmanage.jsp").forward(req, resp);
        /*resp.sendRedirect("Page/managerPage/studentmanage.jsp");*/
    }

    //查询小组详细信息
    protected void queryDetailById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupid = Integer.parseInt(req.getParameter("id"));
        StudentDao studentDao = new StudentDao();
        TeacherDao teacherDao = new TeacherDao();
        GroupDao groupDao = new GroupDao();

        Group group = groupDao.queryById(groupid);
        List<Student> studentList = studentDao.queryByGroupId(groupid);
        List<Teacher> teacherList = teacherDao.queryByGroupId(groupid);
        List<Student> studentListNot = studentDao.queryIsGrouped();
        List<Teacher> teacherListNot = teacherDao.queryIsGrouped();

        req.setAttribute("groupDetail", group);
        req.setAttribute("studentList", studentList);
        req.setAttribute("teacherList", teacherList);
        req.setAttribute("studentListNot", studentListNot);
        req.setAttribute("teacherListNot", teacherListNot);

        req.getRequestDispatcher("/Pages/managerPage/groupdetail.jsp").forward(req, resp);
    }

    //删除小组
    protected void deleteGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Group group = new Group();
        group.setId(id);
        GroupDao groupDao = new GroupDao();
        groupDao.deleteGroup(group);
        HttpSession session=req.getSession();
        List<Group> groupList=groupDao.queryAllGroup();
        session.setAttribute("groupList",groupList);
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);
    }

    //查询没有小组的教师
    protected void queryNoGroupTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        TeacherDao teacherDao = new TeacherDao();
        teacherList = teacherDao.queryIsGrouped();
        HttpSession session = req.getSession();
        session.setAttribute("teacherList", teacherList);
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);
    }

    //添加小组
    protected void addGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("add-name");
        String datestr = req.getParameter("add-year") + "-" + req.getParameter("add-month") + "-" + req.getParameter("add-day") +
                " " + req.getParameter("add-hour") + ":" + req.getParameter("add-minute") + ":00";
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(datestr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int leaderid = Integer.parseInt(req.getParameter("add-leader"));
        Group group = new Group();
        group.setName(name);
        group.setDefencetime(date);
        Teacher teacher = new Teacher();
        teacher.setId(leaderid);
        group.setLeader(teacher);
        TeacherDao teacherDao = new TeacherDao();
        GroupDao groupDao = new GroupDao();
        groupDao.addGroup(group);
        group = groupDao.queryByName(name);
        teacherDao.updateTeacherGroup(leaderid, group.getId(), "groupleader");
        HttpSession session=req.getSession();
        List<Group> groupList=groupDao.queryAllGroup();
        session.setAttribute("groupList",groupList);
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);

    }

    //将老师移除小组
    protected void removeTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacherDao.removeTeacher(teacher);
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);
    }

    //将学生移除小组
    protected void removeStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        student.setId(id);
        studentDao.removeStudent(student);
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);
    }

    //将学生加入小组
    protected void addStudentToGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("add-student"));
        int groupid = Integer.parseInt(req.getParameter("groupid"));
        StudentDao studentDao = new StudentDao();
        studentDao.updateStudentGroup(id, groupid);
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);
    }

    //将教师加入小组
    protected void addTeacherToGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("add-teacher"));
        int groupid = Integer.parseInt(req.getParameter("groupid"));
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.updateTeacherGroup(id, groupid, "teacher");
        req.getRequestDispatcher("GroupServlet?action=searchAllGroup").forward(req, resp);
    }

    //为小组添加考勤
    protected void addCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Check check = new Check();
        int start = Integer.parseInt(req.getParameter("start"));
        int end = Integer.parseInt(req.getParameter("end"));
        int groupid = Integer.parseInt(req.getParameter("groupid"));
        check.setStart(start);
        check.setEnd(end);
        GroupDao groupDao = new GroupDao();
        groupDao.addCheck(check, groupid);
        req.getRequestDispatcher("GroupServlet?action=isChecked").forward(req, resp);
    }

    //查询小组成员的考勤情况
    protected void isChecked(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Teacher teacher = new Teacher();
        teacher = (Teacher) session.getAttribute("user");
        TeacherDao teacherDao = new TeacherDao();

        /*
         * 小组考勤时间范围*/
        Group group=new Group();
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
        req.setAttribute("starttime", starttime);
        req.setAttribute("endtime", endtime);

        List<Teacher> teacherList = new ArrayList<Teacher>();
        teacherList = teacherDao.queryByGroupId(teacher.getGroup().getId());
        req.setAttribute("teacherList", teacherList);
        req.getRequestDispatcher("/Pages/groupleaderPage/checkmanage.jsp").forward(req, resp);

    }
}
