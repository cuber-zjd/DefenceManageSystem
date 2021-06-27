<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/15
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" href="static/css/font-awesome.css">
    <link rel="stylesheet" href="static/css/index.css">

    <script type="text/javascript" src="static/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        //页面加载完成之后
        $(function () {
            //登录表单验证
            $("#btn-login").click(function (){
                if ($("input[name='username-login']").val()==""){
                    alert("账号不能为空");
                    return false;
                }
                var username_login_patt=/^[0-9]{0,9}$/;
                var username_login=$("input[name='username-login']").val();
                if (!username_login_patt.test(username_login)){
                    alert("账号为学号（全为9位数字）");
                    return false;
                }
               if($("input[name='status']:checked").val()==undefined){
                   alert("请选择登录身份")
                   return false;
               }
            });
            //是否登录失败
            function getQueryVariable(variable)
            {
                var query = window.location.search.substring(1);
                var vars = query.split("&");
                for (var i=0;i<vars.length;i++) {
                    var pair = vars[i].split("=");
                    if(pair[1]=="fail"){
                        alert("账号或密码不正确,请重新输入");
                    }
                }
                return(false);
            }
            window.onload=getQueryVariable();
        });

    </script>
</head>

<body>
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
    <path fill="#0099ff" fill-opacity="1"
          d="M0,192L48,197.3C96,203,192,213,288,229.3C384,245,480,267,576,250.7C672,235,768,181,864,181.3C960,181,1056,235,1152,234.7C1248,235,1344,181,1392,154.7L1440,128L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path>
</svg>
<div class="user">
    <i class="fa fa-user"></i>
    <div class="head">account login</div>
</div>
<div class="container">
    <div class="box login">
        <div class="form-content"><h1>欢迎使用</h1>
            <div class="avtar">
                <div class="pic"><img src="static/img/1.jpg" alt=""></div>
            </div>

            <form action="LoginServlet" class="form">
                <input hidden="hidden" name="method" value="login">
                <div>
                    <i class="fa fa-user-o"></i>
                    <input type="text" placeholder="请输入学号或工号" name="username-login">
                </div>
                <div>
                    <i class="fa fa-key"></i>
                    <input type="password" placeholder="请输入密码" name="password-login">
                </div>
                <div>
                    <%--<select name="status">
                        <option value="student">学生</option>
                        <option value="teacher">教师</option>
                    </select>--%>

                    <input type="radio" value="student" name="status" style="width: 1rem ;box-shadow: 0 0 0 0;">学生
                        <input style="box-shadow: 0 0 0 0;width: 1rem">
                    <input type="radio" value="teacher" name="status" style="width: 1rem;box-shadow: 0 0 0 0;">教师
                </div>

                <input type="submit" id="btn-login" value="登录" style="text-align: center;background-color: #3399ff;padding-left: 0.8rem;color: white;font-size: 1rem">
            </form>

        </div>
    </div>

</div>
</body>
</html>