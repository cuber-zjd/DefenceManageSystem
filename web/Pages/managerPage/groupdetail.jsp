<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/20
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/Pages/common/head.jsp" %>
    <title>小组详细信息</title>
</head>
<body>
<div>
    <div class="col-md-3"></div>
    <center class="col-md-6">
        <span>小组名称：${groupDetail.name}</span><br/>
        <span><fmt:formatDate value="${groupDetail.defencetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
        <span>组长：${groupDetail.leader.name}</span><br/>
        <span>教师名单</span>
        <a class="btn btn-primary" data-toggle="modal" data-target="#addTeacher-modal">添加教师</a>
        <a class="btn btn-primary" data-toggle="modal" data-target="#addStudent-modal">添加学生</a>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>身份</th>
                <th>编辑</th>

            </tr>
            <c:forEach items="${teacherList}" var="teacher">
                <tr>
                    <td>${teacher.tno}</td>
                    <td>${teacher.name}</td>
                    <td>${teacher.status}</td>
                    <th><a class="btn btn-danger" href="GroupServlet?action=removeTeacher&id=${teacher.id}">删除</a></th>
                </tr>
            </c:forEach>

        </table>
        <span>学生名单</span>
        <table class="table table-striped table-bordered table-hover">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>编辑</th>
            </tr>
            <c:forEach items="${studentList}" var="student">
                <tr>
                    <td>${student.sno}</td>
                    <td>${student.name}</td>
                    <th><a class="btn btn-danger" href="GroupServlet?action=removeStudent&id=${student.id}">删除</a></th>
                </tr>
            </c:forEach>

        </table>
    </center>

</div>
<!--模态框-添加-->
<div class="modal fade" id="addTeacher-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="GroupServlet?action=addTeacherToGroup" class="form-horizontal" method="post" id="addTeacher-form">
                    <input type="hidden" name="groupid" value="${groupDetail.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">组长</label>
                        <div class="col-sm-7">
                            <select class="form-control " id="add-leader" name="add-teacher">
                                <c:forEach items="${teacherListNot}" var="teacher">
                                    <option value="${teacher.id}">${teacher.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="addTeacher-btn">添加</button>
            </div>
        </div>
    </div>
</div>
<!--模态框-添加-->
<div class="modal fade" id="addStudent-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="GroupServlet?action=addStudentToGroup" class="form-horizontal" method="post" id="addStudent-form">
                    <input type="hidden" name="groupid" value="${groupDetail.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学生</label>
                        <div class="col-sm-7">
                            <select class="form-control " id="add-student" name="add-student">
                                <c:forEach items="${studentListNot}" var="student">
                                    <option value="${student.id}">${student.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="addStudent-btn">添加</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#addStudent-btn").click(function (){
       $("#addStudent-form").submit();
    });
    $("#addTeacher-btn").click(function (){
        $("#addTeacher-form").submit();
    })
</script>
</body>
</html>
