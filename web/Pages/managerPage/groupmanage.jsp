<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/20
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%--静态包含base标签，css，js文件--%>
    <%@include file="/Pages/common/head.jsp" %>
    <title>小组管理</title>
</head>
<body>

<div class="container">
    <!--搜索以及排序-->
    <form class="form-inline" style="margin-top: 15px" action="GroupServlet?action=searchAllGroup" method="post">

        <input type="submit" class="btn btn-primary" value="查看所有小组">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#create">
            添加
        </button>
    </form>
    <!--表格显示-->
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>组名</th>
            <th>答辩时间</th>
            <th>组长</th>
            <th>编辑</th>
        </tr>
        <c:forEach items="${pg.pageData}" var="group">
            <tr>
                <td class="col-md-3">${group.name}</td>
                <td class="col-md-3"><fmt:formatDate value="${group.defencetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="col-md-3">${group.leader.name}</td>
                <td>
                    <a class="btn btn-sm btn-danger col-md-3 col-md-offset-1 active"
                       href="GroupServlet?action=deleteGroup&id=${group.id}">删除</a>
                    <a class="btn btn-sm btn-success col-md-3 col-md-offset-1 active updatebutton"
                       href="GroupServlet?action=queryDetailById&id=${group.id}">小组名单</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation" style="float:right;position: fixed;bottom: 0px;">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
<!--模态框-添加-->
<div class="modal fade" id="create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="GroupServlet?action=addGroup" class="form-horizontal" method="post" id="add-form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">组名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" id="name" name="add-name" placeholder="请输入组名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">时间</label><br/><br/>
                        <div class="col-sm-3">
                            <select class="form-control " id="add-year" name="add-year">
                                <c:forEach begin="2018" end="2030" var="i">
                                    <option value="${i}"><c:out value="${i}"/>年</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-sm-2">
                            <select class="form-control " id="add-month" name="add-month">
                                <c:forEach begin="1" end="12" var="i">
                                    <option value="${i}"><c:out value="${i}"/>月</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-sm-2">
                            <select class="form-control " id="add-day" name="add-day">
                                <c:forEach begin="1" end="31" var="i">
                                    <option value="${i}"><c:out value="${i}"/></option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-sm-2">
                            <select class="form-control " id="add-hour" name="add-hour">
                                <c:forEach begin="1" end="24" var="i">
                                    <option value="${i}"><c:out value="${i}"/>时</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-sm-2">
                            <select class="form-control " id="add-minute" name="add-minute">
                                <c:forEach begin="1" end="60" var="i">
                                    <option value="${i}"><c:out value="${i}"/>分</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">组长</label>
                        <div class="col-sm-7">
                            <select class="form-control " id="add-leader" name="add-leader">
                                <c:forEach items="${sessionScope.teacherList}" var="teacher">
                                    <option value="${teacher.id}">${teacher.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="add-btn">添加</button>
            </div>
        </div>
    </div>
</div>
<!--JavaScript体-->
<script type="text/javascript">
    $(function () {
        $("#add-btn").click(function (){
            $("#add-form").submit();
        });
    });
</script>
</body>
</html>
