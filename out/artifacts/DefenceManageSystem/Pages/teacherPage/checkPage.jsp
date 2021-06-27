<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/20
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/Pages/common/head.jsp" %>
    <title>考勤</title>
    <script type="text/javascript">
        $(function () {
            //点击考勤
            $("#check").click(function () {
                window.location = "TeacherServlet?action=check&tno=${user.tno}";
            });
            //处理考勤时间


            //本组是否设置考勤

            if ("${isSetChecked}" == "true") {
                var starttemp = new Date("${starttime}");
                var temp = starttemp.getTime() - 14 * 60 * 60 * 1000;
                var startdate = new Date(temp);
                var endtemp = new Date("${endtime}");
                temp = endtemp.getTime() - 14 * 60 * 60 * 1000;
                var enddate = new Date(temp);
                var date = new Date;
                if (startdate > date) {
                    $("#notstarttip").css("display", "inline");
                    $("#check").attr("disabled", "disabled")
                } else if (enddate < date) {
                    $("#endedtip").css("display", "inline");
                    $("#check").attr("disabled", "disabled")
                } else {
                    $("#notstarttip").css("display", "none");
                    $("#endedtip").css("display", "none");
                    $("#check").attr("disabled", false);
                }
                //已考勤
                if ("${user.isChecked}"=="是"){
                    $("#checked").css("display", "inline");
                    $("#check").attr("disabled", "disabled");
                }else {
                    //未考勤
                    $("#notcheck").css("display", "inline");
                }
            }else {
                $("#nocheck").css("display", "inline");
                $("#check").attr("disabled", "disabled");
                $("#checktime").css("display","none");
            }
        });
    </script>
</head>
<body>
<center>
    <h1><span id="nocheck" style="display:none;">组长还未设置考勤</span>
    <span id="notstarttip" style="display:none;">考勤还未开始</span>
    <span id="endedtip" style="display:none;">考勤已经结束</span>
    <span id="checked" style="display:none;">已考勤</span>
    <span id="notcheck" style="display:none;">未考勤</span></h1><br/>
    <h1><span class="label label-default" id="checktime">考勤时间  <fmt:formatDate value="${starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>---<fmt:formatDate
            value="${endtime}" pattern="yyyy-MM-dd HH:mm:ss"/> </span></h1><br/>
    <button class="btn btn-primary" id="check" style="border-radius: 100%;height: 200px ;width: 200px"> 考勤</button>
</center>

</body>
</html>
