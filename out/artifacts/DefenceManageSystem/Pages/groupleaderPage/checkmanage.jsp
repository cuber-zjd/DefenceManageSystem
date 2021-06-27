<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/20
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/Pages/common/head.jsp" %>
    <title>考勤管理</title>
    <script type="text/javascript">
        $(function () {

            if ("${isSetChecked}" == "true") {
                $("#setcheck").css("display", "inline");
            } else {
                $("#set-form").attr("hidden", false);
            }
        });
    </script>
</head>
<body>
<div class="col-md-12">
    <center><h1><span class="label label-default " id="setcheck" style="display: none">已设置考勤 时间：<fmt:formatDate
            value="${starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>---<fmt:formatDate
            value="${endtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></h1></center>
</div>

<center>
    <form action="GroupServlet?action=addCheck" method="post" hidden="hidden" id="set-form">
        <input type="hidden" name="groupid" value="${user.group.id}">
        <label>距离考试开始前</label>
        <select name="start">
            <c:forEach begin="1" end="60" var="i">
                <option><c:out value="${i}"/></option>
            </c:forEach>
        </select>
        分钟<br/>
        至考试开始后
        <select name="end">
            <c:forEach begin="1" end="60" var="i">
                <option><c:out value="${i}"/></option>
            </c:forEach>
        </select>分钟
        <input type="submit" class="btn btn-primary" value="提交">
    </form>
</center>
<div class="col-md-3"></div>
<div class="col-md-6">
    <table style="margin-top: 20px">
        <table class="table table-striped table-bordered table-hover" style="text-align: center">
            <tr>
                <th>姓名</th>
                <th>是否考勤</th>
            </tr>
            <c:forEach items="${teacherList}" var="teacher">
                <tr>
                    <td>${teacher.name}</td>
                    <td id="context">${teacher.isChecked}</td>
                </tr>
            </c:forEach>

        </table>
    </table>
</div>

</body>
</html>
