<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/18
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/Pages/common/head.jsp"%>
    <title>Title</title>
</head>
<body>

<div class="col-md-3"></div>
<div class="col-md-6" style="margin-top: 20px">
    <form action="StudentServlet?action=mark" method="post" class="form-horizontal">
        <input type="hidden" name="tno" value="${user.tno}">
        <div class="form-group">
            <label class="col-md-2 control-label">请选择要打分的学生</label>
            <div class="col-md-7">
                <select class="form-control" name="studentsno">
                    <c:forEach items="${studentList}" var="student">
                        <option value="${student.sno}"> ${student.name}</option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">论文内容</label>
            <div class="col-md-7">
                <input class="form-control" type="number" name="context" placeholder="0 - 50 分">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">创新</label>
            <div class="col-md-7">
                <input class="form-control" type="number" name="innovate" placeholder="0 - 10 分">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">答辩</label>
            <div class="col-md-7">
                <input class="form-control"  type="number" name="defence" placeholder="0 - 30 分">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">所用时间</label>
            <div class="col-md-7">
                <input class="form-control" type="number" name="time" placeholder="0 - 10 分">
            </div>
        </div>
        <center><input class="btn btn-primary" type="submit" value="提交"></center>
    </form>
</div>
</body>
</html>
