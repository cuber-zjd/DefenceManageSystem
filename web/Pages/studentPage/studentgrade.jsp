<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <%--静态包含base标签，css，js文件--%>
    <%@include file="/Pages/common/head.jsp"%>
    <title>学生成绩</title>

    <script type="text/javascript">
        $(function (){

                $.ajax({
                    url:"http://localhost:8080/DefenceManageSystem/StudentServlet",
                    data:"action=getGrade&sno=${user.sno}",
                    type:"post",
                    success:function (data){
                        $("#context").text(data.context);
                        $("#innovate").text(data.innovate);
                        $("#defence").text(data.defence);
                        $("#time").text(data.time);
                        $("#grade").text(data.grade);
                    },
                    dataType:"json"
                })

        })



    </script>
</head>
<body>
<center style="margin-top: 50px">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <table class="table table-striped table-bordered table-hover" style="text-align: center">
            <tr>
                <th>得分项</th>
                <th>得分</th>
            </tr>
            <tr>
                <td>内容</td>
                <td id="context"></td>
            </tr>
            <tr>
                <td>创新</td>
                <td id="innovate"></td>
            </tr>
            <tr>
                <td>答辩</td>
                <td id="defence"></td>
            </tr>
            <tr>
                <td>时间得分</td>
                <td id="time"></td>
            </tr>
            <tr>
                <td>总分</td>
                <td id="grade"></td>
            </tr>
        </table>

    </div>

</center>

<script type="text/javascript" src="../../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>