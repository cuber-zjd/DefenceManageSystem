package com.dy.servlet; /**
 * @author: zjd-DY
 * @date: 2021/6/18 12:12
 * @description:
 */

import com.dy.dao.impl.GradeDao;
import com.dy.dao.impl.GroupDao;
import com.dy.dao.impl.StudentDao;
import com.dy.entity.GradeList;
import com.dy.entity.Group;
import com.dy.entity.Student;
import com.dy.entity.Teacher;
import com.dy.utils.DBUtils;
import com.dy.utils.PageBeanUtils;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends BaseServlet {
    //获取个人成绩
    protected void getGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sno = request.getParameter("sno");
        GradeDao gradeDao=new GradeDao();
        GradeList grade = gradeDao.getGrade(Integer.parseInt(sno));
        Gson gson = new Gson();
        String gradeJsonString = gson.toJson(grade);
        response.getWriter().write(gradeJsonString);
    }

    //上传论文
    protected void uploadPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) ;
        //创建FileItemFactory工厂实现类
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        //创建用于解析上传数据的工具类ServletFileUpload类
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        try {
            //解析上传的数据，得到每一个表单FileItem
            List<FileItem> list = servletFileUpload.parseRequest(req);

            for (FileItem fileItem : list) {

                if (fileItem.isFormField()){
                    //普通表单项
                } else {
                    //上传的文件
                    HttpSession session=req.getSession();
                    Student student=(Student) session.getAttribute("user");
                    fileItem.write(new File("D:\\workspace\\workspace-idea\\DefenceManageSystem\\web\\paper\\"+student.getSno()+".pdf"));
                    StudentDao studentDao=new StudentDao();
                    studentDao.addUrl(student.getSno(),"paper/"+student.getSno()+".pdf");
                    resp.sendRedirect("Pages/studentPage/uploadPaper.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断是否以上传文档
    protected void isOnloaded(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Student student=(Student) session.getAttribute("user");
        StudentDao studentDao=new StudentDao();
        student=studentDao.queryBySno(student.getSno());
        String result;
        if(student.getPaperUrl()!=null&&(!"".equals(student.getPaperUrl()))){
            result="success";
        }else {
            result="fail";
        }
        resp.getWriter().write(result);
    }

    //通过姓名查询学生所有信息
    protected void searchAllStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String kw=req.getParameter("kw");
        req.setAttribute("kw",kw);
        StudentDao studentDao=new StudentDao();
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
            totalData=studentDao.getTotalNumByName(kw);

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
        List<Student> studentList=null;
        studentList=studentDao.queryByName(kw,currentPage,pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg=new PageBeanUtils(currentPage,pageSize,totalData);
        pg.setPageData(studentList);
        req.setAttribute("pg",pg);
        req.getRequestDispatcher("/Pages/managerPage/studentmanage.jsp").forward(req,resp);
        /*resp.sendRedirect("Page/managerPage/studentmanage.jsp");*/
    }

    //修改学生信息
    protected void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("edit-id"));
        String name=req.getParameter("edit-name");
        String sex=req.getParameter("edit-sex");
        int groupid=Integer.parseInt(req.getParameter("edit-group"));
        Student student=new Student();
        student.setId(id);
        student.setSex(sex);
        student.setName(name);
        Group group=new Group();
        group.setId(groupid);
        student.setGroup(group);
        StudentDao studentDao=new StudentDao();
        studentDao.updateStudent(student);
        req.getRequestDispatcher("StudentServlet?action=searchAllStudent&kw=").forward(req,resp);
    }

    //添加学生
    protected void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sno=Integer.parseInt(req.getParameter("sno"));
        String name=req.getParameter("name");
        String sex=req.getParameter("sex");
        int groupid=Integer.parseInt(req.getParameter("group"));
        Student student=new Student();
        student.setSno(sno);
        student.setName(name);
        student.setSex(sex);
        Group group=new Group();
        group.setId(groupid);
        student.setGroup(group);
        StudentDao studentDao=new StudentDao();
        studentDao.addStudent(student);
        req.getRequestDispatcher("StudentServlet?action=searchAllStudent&kw=").forward(req,resp);
    }

    //删除学生
    protected void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        Student student=new Student();
        student.setId(id);
        StudentDao studentDao=new StudentDao();
        studentDao.deleteStudent(student);
        req.getRequestDispatcher("StudentServlet?action=searchAllStudent&kw=").forward(req,resp);
    }

    //查询某组学生成绩
    protected void queryStudentByGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置当前页数（抛出异常，小于1时，设置当前页数为1）
        HttpSession session=req.getSession();
        Teacher teacher=new Teacher();
        teacher=(Teacher)session.getAttribute("user");
        StudentDao studentDao=new StudentDao();
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
            totalData=studentDao.getTotalNumByGroupId(teacher.getGroup().getId());

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
        List<Student> studentList=null;
        studentList=studentDao.queryByGroupId(teacher.getGroup().getId(),currentPage,pageSize);
        //创建page类，存储页码信息及所有的学生信息
        PageBeanUtils pg=new PageBeanUtils(currentPage,pageSize,totalData);
        pg.setPageData(studentList);
        req.setAttribute("pg",pg);
        req.getRequestDispatcher("/Pages/teacherPage/checkdefence.jsp").forward(req,resp);
    }

    //查询小组成员
    protected void queryAllStudentByGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Teacher teacher=new Teacher();
        teacher=(Teacher) session.getAttribute("user");
        StudentDao studentDao=new StudentDao();
        List<Student> studentList=studentDao.queryByGroupId(teacher.getGroup().getId(),teacher.getTno());
        req.setAttribute("studentList",studentList);
        req.getRequestDispatcher("Pages/teacherPage/markPage.jsp").forward(req,resp);
    }

    //评分
    protected void mark(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentsno=Integer.parseInt(req.getParameter("studentsno"));
        int context=Integer.parseInt(req.getParameter("context"));
        int innovate=Integer.parseInt(req.getParameter("innovate"));
        int defence=Integer.parseInt(req.getParameter("defence"));
        int time=Integer.parseInt(req.getParameter("time"));
        int total=context+innovate+time+defence;
        int tno=Integer.parseInt(req.getParameter("tno"));
        GradeList grade=new GradeList(context,innovate,defence,time,total);
        StudentDao studentDao=new StudentDao();
        studentDao.mark(studentsno,grade,tno);
        req.getRequestDispatcher("StudentServlet?action=queryStudentByGroup").forward(req,resp);
    }

    //查看论文已经上传的本组学生
    protected void checkPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Teacher teacher=new Teacher();
        teacher=(Teacher) session.getAttribute("user");
        StudentDao studentDao=new StudentDao();
        List<Student> studentList=studentDao.queryByGroupId(teacher.getGroup().getId());
        req.setAttribute("studentList",studentList);
        req.getRequestDispatcher("/Pages/teacherPage/checkPaper.jsp").forward(req,resp);

    }
}
