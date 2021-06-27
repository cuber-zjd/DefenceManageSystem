<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/23
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/Pages/common/head.jsp"%>
    <title>Title</title>
    <script>
        $(function () {

           /* $.ajax({
                url: "http://localhost:8080/DefenceManageSystem/StudentServlet",
                type: "post",
                data: "action=isOnloaded",
                success: function (data) {
                    if (data == "success") {
                        $("#upload-form").attr("hidden", "hidden");

                        $("#onloaded").attr("hidden", false);
                    } else {
                        $("#checkpaper").css("display", "none");
                    }
                },
                dataType: "text"
            });*/
            $("#checkpaper").click(function () {
                var url =  $("option:selected").val();
                window.open("http://localhost:8080/DefenceManageSystem/" + url + "?r=" + Math.random());
            });

        });
    </script>
</head>
<body>
<div class="col-md-3">
</div>
<div class="col-md-6">
    <center><span class="h2 col-md-12" id="onloaded" hidden="hidden">论文已上传，点击按钮查看</span></center>
    <select class="form-control" id="url">
        <c:forEach items="${studentList}" var="student">
            <c:if test="${!empty student.paperUrl}">
                <option value="${student.paperUrl}">${student.name}</option>
            </c:if>

        </c:forEach>

    </select>
    <center>
        <button id="checkpaper" class="btn btn-primary">查看我的论文</button>
    </center>
</div>
</body>
</html>
