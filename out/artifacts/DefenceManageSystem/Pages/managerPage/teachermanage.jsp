<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/19
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教师管理</title>
    <%@include file="/Pages/common/head.jsp"%>
</head>
<body>

<div class="container">
    <!--搜索以及排序-->
    <form class="form-inline" style="margin-top: 15px" action="TeacherServlet?action=searchAllTeacher" method="post">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="请输入姓名搜索" name="kw" value="${kw}">
        </div>
        <input class="btn btn-primary" type="submit" value="搜索">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#create">
            添加
        </button>
    </form>
    <!--表格显示-->
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>工号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>小组</th>
            <th>身份</th>
        </tr>
        <c:forEach items="${pg.pageData}" var="teacher">
            <tr>
                <td class="col-md-1">${teacher.tno}</td>
                <td class="col-md-2">${teacher.name}</td>
                <td class="col-md-1">${teacher.sex}</td>
                <td class="col-md-1">${teacher.group.name}</td>
                <td class="col-md-2">${teacher.status}</td>
                <td ><a class="btn btn-sm btn-success col-md-2 col-md-offset-2 active updatebutton" name="${teacher.id}">编辑</a>
                    <a class="btn btn-sm btn-danger col-md-2 col-md-offset-2 active" href="TeacherServlet?action=deleteTeacher&id=${teacher.id}" >删除</a></td>
                <input type="hidden" id="${teacher.id}" value="${teacher.tno}&${teacher.name}&${teacher.sex}&${teacher.group.id}&${teacher.status}">
            </tr>
        </c:forEach>

    </table>
    <%--分页导航--%>
    <nav aria-label="Page navigation" style="float:right;position: fixed;bottom: 0px;">
        <ul class="pagination">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="TeacherServlet?action=searchAllTeacher&curPage=${pg.firstpage}&kw=${kw}">首页</a></li>
                <li class="page-item"><a class="page-link" href="TeacherServlet?action=searchAllTeacher&curPage=${pg.currentpage-1}&kw=${kw}">上一页</a></li>
                <li class="page-item"><a class="page-link" href="TeacherServlet?action=searchAllTeacher&curPage=${pg.currentpage+1}&kw=${kw}">下一页</a></li>
                <li class="page-item"><a class="page-link" href="TeacherServlet?action=searchAllTeacher&curPage=${pg.lastpage}&kw=${kw}">尾页</a></li>
                <li class="page-item"><a class="page-link">当前第${pg.currentpage}/${pg.totalpage}页</a></li>
            </ul>
        </ul>
    </nav>
</div>
<!--模态框-添加-->
<div class="modal fade" id="create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="TeacherServlet?action=addTeacher&kw=" class="form-horizontal" method="post" id="add-form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">工号</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" placeholder="请输入工号" name="add-tno">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" id="name" placeholder="请输入姓名" name="add-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">性别</label>
                        <label class="radio-inline col-sm-offset-1">
                            <input type="radio" id="inlineRadio2" value="男" name="add-sex"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio"  id="inlineRadio3" value="女" name="add-sex"> 女
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">小组</label>
                        <div class="col-sm-7">
                            <select class="form-control " name="add-group">
                                <c:forEach items="${sessionScope.groupList}" var="group">
                                    <option value="${group.id}" name="edit-option">${group.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" >身份</label>
                        <div class="col-sm-7">
                            <select class="form-control " name="add-status">
                                <option value="teacher">教师</option>
                                <option value="groupleader">教师组长</option>
                                <option value="manager">管理员</option>
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
<!--模态框-修改-->
<div class="modal fade" id="updatewin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >修改</h4>
            </div>
            <div class="modal-body">
                <!--修改表单-->
                <form action="TeacherServlet?action=updateTeacher" class="form-horizontal" id="edit-form" method="post">
                    <input type="hidden" name="edit-id">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" >姓名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" name="edit-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" >性别</label>
                        <label class="radio-inline col-sm-offset-1">
                            <input type="radio"  value="男" name="edit-sex"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio"   value="女" name="edit-sex"> 女
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">小组</label>
                        <div class="col-sm-7">
                            <select class="form-control " name="edit-group">
                                <c:forEach items="${sessionScope.groupList}" var="group">
                                    <option value="${group.id}" name="edit-option">${group.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" >身份</label>
                        <div class="col-sm-7">
                            <select class="form-control " name="edit-status">
                                <option value="teacher">教师</option>
                                <option value="groupleader">教师组长</option>
                                <option value="manager">管理员</option>
                            </select>
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="edit-btn">修改</button>
            </div>
        </div>
    </div>
</div>
<!--JavaScript体-->
<script type="text/javascript">
    $(".updatebutton").click(function (){
        var mes=$("#"+this.name).val()
        var strs=mes.split("&");
        $("input[name='edit-name']").val(strs[1]);
        $("input[name='edit-sex']").val([strs[2]]);
        var flag=false;
        $("select[name='edit-group']").find("option").each(function (){
             if ($(this).val()==strs[3]){
                 $(this).attr("selected", "selected");
                 flag=true;
             }
        })
        if (flag==false){
            $("#edit-group:first").prop("selected",'selected')
        }
        $("input[name='edit-id']").val(this.name);
        $("#updatewin").modal("show");
    });
    $("#edit-btn").click(function (){
       $("#edit-form").submit();
    });
    $("#add-btn").click(function (){
       $("#add-form").submit();
    });
</script>

</body>
</html>
