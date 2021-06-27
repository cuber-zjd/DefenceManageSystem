<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/18
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--静态包含base标签，css，js文件--%>
    <%@include file="/Pages/common/head.jsp"%>
    <title>学生信息管理</title>

    <style type="text/css">
        .h-pd {
            padding: 0px !important;
        }
    </style>
</head>
<body>

<div class="container">
    <!--搜索以及排序-->
    <form class="form-inline" style="margin-top: 15px" action="StudentServlet?action=searchAllStudent" method="post">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="请输入姓名搜索" name="kw" value="${kw}">
        </div>

        <input class="btn btn-primary" type="submit" value="搜索">
        <button type="button" class="btn btn-primary createbtn" data-toggle="modal">
            添加
        </button>
    </form>
    <!--表格显示-->
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>成绩</th>
            <th>小组</th>
            <th>编辑</th>
        </tr>
       <c:forEach items="${pg.pageData}" var="student">
            <tr>
                <td class="col-md-2">${student.sno}</td>
                <td class="col-md-2">${student.name}</td>
                <td class="col-md-2">${student.grade.grade}</td>
                <td class="col-md-2">${student.group.name}</td>
                <td><a class="btn btn-sm btn-success col-md-3 active updatebutton" name="${student.id}">编辑</a><a
                        class="btn btn-sm btn-danger col-md-3 col-md-offset-1 active" href="StudentServlet?action=deleteStudent&id=${student.id}">删除</a>
                    <a class="btn btn-sm btn-primary col-md-3 col-md-offset-1 active checkgrade" name="${student.id}">查看成绩</a>
                </td>
                <input type="hidden" id="${student.id}" value="${student.sno}&${student.name}&${student.sex}&${student.group.id}&${student.grade.context}&${student.grade.innovate}&${student.grade.defence}&${student.grade.time}&${student.grade.grade}">
            </tr>
        </c:forEach>



    </table>
    <%--分页导航--%>
    <nav aria-label="Page navigation" style="float:right;position: fixed;bottom: 0px;">
        <ul class="pagination">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="StudentServlet?action=searchAllStudent&curPage=${pg.firstpage}&kw=${kw}">首页</a></li>
                <li class="page-item"><a class="page-link" href="StudentServlet?action=searchAllStudent&curPage=${pg.currentpage-1}&kw=${kw}">上一页</a></li>
                <li class="page-item"><a class="page-link" href="StudentServlet?action=searchAllStudent&curPage=${pg.currentpage+1}&kw=${kw}">下一页</a></li>
                <li class="page-item"><a class="page-link" href="StudentServlet?action=searchAllStudent&curPage=${pg.lastpage}&kw=${kw}">尾页</a></li>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="StudentServlet?action=addStudent" class="form-horizontal" id="add-form" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" name="sno" placeholder="请输入学号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" name="name" placeholder="请输入姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <label class="radio-inline col-sm-offset-1">
                            <input type="radio" id="inlineRadio2" value="男" name="sex"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" id="inlineRadio3" value="女" name="sex"> 女
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">小组</label>
                        <div class="col-sm-7">
                            <select class="form-control " name="group">
                                <c:forEach items="${sessionScope.groupList}" var="group">
                                    <option value="${group.id}" name="edit-option">${group.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="addstu">添加</button>
            </div>
        </div>
    </div>
</div>
<!--模态框-修改-->
<div class="modal fade" id="updatewin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">
                <!--修改表单-->
                <form action="StudentServlet?action=updateStudent" class="form-horizontal" id="edit-form" method="post">
                    <input type="hidden" name="edit-id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" name="edit-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <label class="radio-inline col-sm-offset-1">
                            <input type="radio" value="男" name="edit-sex"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" value="女" name="edit-sex"> 女
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">小组</label>
                        <div class="col-sm-7">
                            <select class="form-control " name="edit-group" id="edit-group">
                                <c:forEach items="${sessionScope.groupList}" var="group">
                                    <option value="${group.id}" name="edit-option">${group.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="edit">修改</button>
            </div>
        </div>
    </div>
</div>
<%--模态框-查看详细成绩--%>
<div class="modal fade" id="check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细成绩单</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
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
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--JavaScript体-->
<script type="text/javascript">

    $(function () {
        //添加数据
        $(".createbtn").click(function () {
            $("#create").modal("show");
        });
        //修改信息
        $(".updatebutton").click(function () {
            var mes=$("#"+this.name).val()
            var strs=mes.split("&");
            //获取相关信息
            $("input[name='edit-name']").val(strs[1]);
            $("input[name='edit-sex']").val([strs[2]])
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
        //修改表单提交
        $("#edit").click(function (){
            $("#edit-form").submit();
        });
        //添加表单提交
        $("#addstu").click(function (){
           $("#add-form").submit();
        });
        //查看详细成绩
        $(".checkgrade").click(function (){
            var mes=$("#"+this.name).val()
            var strs=mes.split("&");
            $("#context").text(strs[4])
            $("#innovate").text(strs[5])
            $("#defence").text(strs[6])
            $("#time").text(strs[7])
            $("#grade").text(strs[8])
            $("#check").modal("show");
        });
    });
</script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</body>
</html>
