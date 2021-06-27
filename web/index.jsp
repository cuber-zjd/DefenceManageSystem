<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty user }">
    <a href="login_register.jsp">请登录</a>
</c:if>
<c:if test="${!empty user }">
    <html>
    <head>
            <%--静态包含base标签，css，js文件--%>
        <%@include file="/Pages/common/head.jsp"%>
        <title>首页</title>
        <style type="text/css">
            html, body {
                height: 100%;
                width: 100%;
            }

            .wrap {
                min-height: 100%;
                height: auto !important;
                margin-bottom: -20px;
            }

            .footer {
                border-top: 1px solid #e5e5e5;
                color: #777;
                background-color: #f5f5f5;
            }

            a:hover {
                text-decoration-line: none;
            }

            a:link {
                text-decoration-line: none;

            }

            a:visited {
                color: #409ee4;
            }

            .h-pd {
                padding: 0px !important;
            }
        </style>

    </head>
    <body>

    <div class="wrap container col-md-12 h-pd" style="height: 100%">
        <!--左侧菜单栏-->
        <div id="contain" class="col-md-2 h-pd" style="height: 100%;background-color:#304156 ;border-radius: unset">
            <!--树形菜单-->
            <div class="panel-group" id="accordion">
                <!--学生功能菜单-->
                <div class="panel student" style="background-color: #304156; color: #b1cbd0 ">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse1">
                                学生功能<span class="glyphicon glyphicon-chevron-down" style="float: right"> </span>
                            </a>
                        </h4>
                    </div>
                    <div class="panel collapse collapse1" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="checkgrade">查看我的成绩</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse1" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="uploadpaper">上传论文</a>
                        </div>
                    </div>

                </div>
                <!--教师组长菜单-->
                <div class="panel groupleader" style="background-color: #304156; color: #b1cbd0 ">

                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse2">
                                教师组长功能<span class="glyphicon glyphicon-chevron-down" style="float: right"> </span>
                            </a>
                        </h4>
                    </div>
                    <div class="panel collapse collapse2" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="checkmanagebtn">考勤管理</a>
                        </div>
                    </div>
                </div>
                <!--教师菜单-->
                <div class="panel teacher" style="background-color: #304156; color: #b1cbd0 ">

                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse3">
                                教师功能<span class="glyphicon glyphicon-chevron-down" style="float: right"> </span>
                            </a>
                        </h4>
                    </div>
                    <div class="panel collapse collapse3" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="mark">答辩评分</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse3" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="check">考勤</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse3" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="checkdefence">查看答辩信息</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse3" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="checkpaper">查看论文</a>
                        </div>
                    </div>
                </div>
                <!--管理员菜单-->
                <div class="panel manager" style="background-color: #304156; color: #b1cbd0 ">

                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse4">管理员功能<span class="glyphicon glyphicon-chevron-down"
                                                            style="float: right"> </span>
                            </a>
                        </h4>
                    </div>
                    <div class="panel collapse collapse4" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="stumanagebtn">学生管理</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse4" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="teamanagebtn">教师管理</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse4" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="groupmanagebtn">分组管理</a>
                        </div>
                    </div>
                    <div class="panel collapse collapse4" style="background-color: #263445">
                        <div class="panel-body">
                            <a id="defencemes">答辩情况</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
            <%--右侧内容栏--%>
        <div class=" col-md-10 h-pd">
            <!--导航-->
            <nav class="navbar navbar-default" style="margin-bottom: 0px">
                <div class="container-fluid">
                    <div class="navbar-header container row">
                        <div class="navbar-brand col-md-"> <span >当前用户：${user.name}  当前权限：${status}</span></div>

                        <div class="navbar-brand col-md-2 col-md-offset-2"> <a  href="#" style="float: right">
                            退出
                        </a></div>
                    </div>
                </div>
            </nav>
            <!--标签页-->
            <iframe id="context" class="col-md-12 h-pd" frameborder=0 scrolling='auto' src="welcomePage.html"></iframe>
        </div>
    </div>
    <!--页脚-->
    <div class="footer ">
        <div class="container" style="text-align: center">
            Copyright &copy;2021 2018级软件工程3班 3组.
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            var tabs = $("ul.nav-tabs");
            //设置左侧导航栏高度
            function setcontainh() {
                var Height = document.documentElement.clientHeight;
                $("#contain").css("height", Height - 20 + 'px');
                $("#context").css("height", Height - 20 - $(".navbar-default").height() + 'px')
            }

            //设置iframe.src控制内容栏显示什么内容
            $("#checkgrade").click(function () {
                $("#context").attr("src", "Pages/studentPage/studentgrade.jsp");
            });
            $("#uploadpaper").click(function (){
                $("#context").attr("src", "Pages/studentPage/uploadPaper.jsp");
            });
            $("#stumanagebtn").click(function (){
                $("#context").attr("src", "StudentServlet?action=searchAllStudent&kw=");
            });
            $("#teamanagebtn").click(function (){
               $("#context").attr("src","TeacherServlet?action=searchAllTeacher&kw=");
            });
            $("#groupmanagebtn").click(function (){
               $("#context").attr("src","GroupServlet?action=queryNoGroupTeacher")
            });
            $("#defencemes").click(function (){
                $("#context").attr("src","Pages/managerPage/chart.jsp")
            });
            $("#checkmanagebtn").click(function (){
                $("#context").attr("src","GroupServlet?action=isChecked")
            });
            $("#check").click(function (){
                $("#context").attr("src","TeacherServlet?action=queryCheckTimeByGroupId")
            });
            $("#checkdefence").click(function (){
                $("#context").attr("src","StudentServlet?action=queryStudentByGroup")
            });
            $("#mark").click(function (){
                $("#context").attr("src","StudentServlet?action=queryAllStudentByGroup")
            });
            $("#checkpaper").click(function (){
                $("#context").attr("src","StudentServlet?action=checkPaper")
            });
            //根据登录身份显示侧边栏导航内容
            var nowstatus = "${status}";
            var user = "${user}"
            if ("${status}" == "student") {
                $(".teacher").attr("hidden", "hidden");
                $(".groupleader").attr("hidden", "hidden");
                $(".manager").attr("hidden", "hidden");
            } else if ("${status}"=="teacher"){
                $(".student").attr("hidden", "hidden");
                $(".groupleader").attr("hidden", "hidden");
                $(".manager").attr("hidden", "hidden");
            } else if ("${status}"=="manager"){
                $(".student").attr("hidden", "hidden");
                $(".groupleader").attr("hidden", "hidden");
                $(".teacher").attr("hidden", "hidden");
            }else if ("${status}"=="groupleader"){
                $(".student").attr("hidden", "hidden");
                $(".manager").attr("hidden", "hidden");
            }
            window.onload = setcontainh;
        });
    </script>

    </body>
    </html>
</c:if>