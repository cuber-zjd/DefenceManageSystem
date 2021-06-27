<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/20
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/Pages/common/head.jsp"%>
    <title>查看答辩信息</title>
</head>
<body>
<div class="col-md-3"></div>
<div class="col-md-6">
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>成绩</th>
            <th>小组</th>
        </tr>
        <c:forEach items="${pg.pageData}" var="student">
            <tr>
                <td class="col-md-3">${student.sno}</td>
                <td class="col-md-3">${student.name}</td>
                <td class="col-md-3">${student.grade.grade}</td>
                <td class="col-md-3">${student.group.name}</td>
                <input type="hidden" id="${student.id}" value="${student.sno}&${student.name}&${student.sex}&${student.group.id}&${student.grade.context}&${student.grade.innovate}&${student.grade.defence}&${student.grade.time}&${student.grade.grade}">
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
