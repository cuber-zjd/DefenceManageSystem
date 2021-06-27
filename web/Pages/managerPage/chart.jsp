<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/20
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
    <%@include file="/Pages/common/head.jsp" %>

    <title>ECharts</title>
    <script type="text/javascript">
        var counts = new Array();
        $(function () {
            $.ajax({
                url: "http://localhost:8080/DefenceManageSystem/GradeServlet",
                data: "action=queryCounts",
                type: "post",
                success: function (data) {
                    counts = data.split("");

                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '答辩情况柱状图'
                        },
                        tooltip: {},
                        legend: {
                            data: ['人数']
                        },
                        xAxis: {
                            data: ["40以下", "40-59", "60-79", "80-89", "90-100"]
                        },
                        yAxis: {},
                        series: [{
                            name: '人数',
                            type: 'bar',
                            data: [Number(counts[1]), Number(counts[3]), Number(counts[5]), Number(counts[7]), Number(counts[9])]
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                },
                dataType: "text"
            });

        });
        window.onload=function (){

        }
    </script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    {

    }

</script>
</body>
</html>