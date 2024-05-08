<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/admin.css">
    <script src="${pageContext.request.contextPath}/static/manager/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/manager/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 管理员列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search">
            <li>
                <a class="button border-main icon-plus-square-o"
                   href="${pageContext.request.contextPath}/manager/admin/openAddUpdate">
                    添加管理员</a>
                <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
                <a class="button border-red icon-plus-square-o" href="javascript:void(0)"
                   onclick="batchDelete()"> 批量删除</a>
            </li>
            <li style="float: right;">
                <input type="text" placeholder="请输入待搜索的名称或账户或手机号" name="keywords" id="keywords" class="input" onkeydown="return search(event)" style="width:250px; line-height:17px;display:inline-block"/>
                <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()">搜索</a>
            </li>
            <li style="float: right;">
                <select id="state" name="state" class="input" onchange="changesearch()" style="width:80px; line-height:17px;display:inline-block">
                    <option value="">状态</option>
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
            </li>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th style="text-align:left; padding-left:20px;"><input type="checkbox" id="cbTrigger"/></th>
            <th>序号</th>
            <th>姓名</th>
            <th>登录名</th>
            <th>头像</th>
            <th>手机号</th>
            <th>邮箱</th>
            <th>微信</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${pageBean.result}" var="admin" varStatus="vs">
                <tr>
                    <td style="text-align:left; padding-left:20px;">
                        <input type="checkbox" name="idCB" value="${admin.id}">
                    </td>
                    <td> ${vs.count} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/admin/details?id=${admin.id}">${admin.nickname}</a>
                    </td>
                    <td>${admin.account}</td>
                    <td><img src="http://127.0.0.1:9005/wego_resources_server/admin/${admin.avatar}" style=" width: 100px"></td>
                    <td>${admin.phone}</td>
                    <td>${admin.email}</td>
                    <td>${admin.wechat}</td>
                    <td>
                        <c:if test="${admin.state == 1}">
                            <a href="javascript:void(0);" onclick="changeState(${admin.id},${admin.state})" style="border: 1px solid #1a1b1c;background: #03b6fd;padding: 2px 2px;">可用</a>
                        </c:if>
                        <c:if test="${admin.state == 0}">
                            <a href="javascript:void(0);" onclick="changeState(${admin.id},${admin.state})" style="border: 1px solid #1a1b1c;background: #fd0391;padding: 2px 2px;">不可用</a>
                        </c:if>
                    </td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/admin/details?id=${admin.id}">
                                <span class="icon-edit"></span> 详情
                            </a>
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/admin/openAddUpdate?id=${admin.id}">
                                <span class="icon-edit"></span> 修改
                            </a>
                            <a class="button border-red" href="javascript:void(0)" onclick="deleteById(${admin.id})">
                                <span class="icon-trash-o"></span> 删除
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <%@include file="_page.jsp" %>
                    <%--指定引入页面--%>
                </td>
            </tr>
        </volist>
    </table>
</div>

<script type="text/javascript">
    $(function () {
        //查询条件数据回显
        $("#state option[value=${adminQuery.state}]").attr("selected", true);
        $("#keywords").val('${adminQuery.nickname}')

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //搜索
    function changesearch() {
        let stateValue = $("#state").val();
        let keywordsValue = $("#keywords").val();
        let param = "state=" + stateValue + "&keywords=" + keywordsValue;
        document.location.href = "${pageContext.request.contextPath}/manager/admin/list?" + param;
    }

    //搜索文件输入框回车自动查询
    function search(event) {
        let keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        //判断是否为回车键
        if (keyCode == 13) {
            changesearch();
        }
    }

    //修改状态
    function changeState(id, state) {
        if (confirm("您确定要修改吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/admin/changeState?id=" + id + "&state=" + state;
        }
    }

    //单个删除
    function deleteById(id) {
        if (confirm("您确定要删除吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/admin/deleteById?id=" + id;
        }
    }

    //复选框全选or取消全选
    $("#cbTrigger").change(function () {
        if ($("#cbTrigger").is(':checked')) {
            $("input[name='idCB']").prop("checked", true);
        } else {
            $("input[name='idCB']").prop('checked', false);
        }
    })

    //批量删除
    function batchDelete() {
        //获取用户选中的数据的主键的集合
        let ids = "";
        $('input:checkbox[name=idCB]:checked').each(function (i) {
            ids += $(this).val() + ",";
        });
        ids = ids.substring(0, ids.length - 1);

        if (ids != "") {
            let t = confirm("您确认要删除选中的内容吗？");
            if (t) {
                ids += ",";
                ids = ids.replace(/_\d+,/g, ",");
                ids = ids.substring(0, ids.length - 1);

                document.location.href = "${pageContext.request.contextPath}/manager/admin/batchDelete?ids=" + ids;
                return true;
            }
        } else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }
</script>
</body>
</html>