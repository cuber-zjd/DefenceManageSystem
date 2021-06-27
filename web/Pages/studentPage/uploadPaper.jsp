<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/18
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--静态包含base标签，css，js文件--%>
    <%@include file="/Pages/common/head.jsp" %>
    <script>
        $(function () {

            $.ajax({
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
            });
            $("#checkpaper").click(function () {
                var url = "paper/" + "${user.sno}" + ".pdf";
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
    <center>
        <form class="form-horizontal" id="upload-form" action="StudentServlet?action=uploadPaper" method="post"
              enctype="multipart/form-data">
            <div class="form-group">
                <label class="col-md-2 control-label">论文：</label>
                <div class="col-md-7">
                    <input type="file" name="paper" class="form-control">
                </div>
            </div>
            <button class="btn btn-primary" id="upload">上传</button>
        </form>
        <br/>
    </center>
    <center>
        <button id="checkpaper" class="btn btn-primary">查看我的论文</button>
    </center>
</div>


</body>
</html>
