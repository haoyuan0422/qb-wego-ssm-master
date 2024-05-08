<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/admin.css">
    <script src="${pageContext.request.contextPath}/static/manager/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/manager/js/pintuer.js"></script>
    <style>
        .search {
            font-size: larger;
        }
    </style>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 支付方式列表</strong>
            <a href="" style="float:right; display:none;">添加字段</a>
        </div>
        <div class="padding border-bottom">
            <ul class="search">
                <li>
                    <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/manager/payMethod/openAddUpdate"> 添加支付方式</a>
                    <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                    <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
                    <a class="button border-red icon-plus-square-o" href="javascript:void(0)" onclick="batchDelete()"> 批量删除</a>
                </li>
                <li style="float: right;">
                    <input type="text" placeholder="请输入搜索的支付方式名称" name="keywords" id="keywords" class="input" style="width:250px; line-height:17px;display:inline-block"/>
                    <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()"> 搜索</a>
                </li>
                <li style="float: right;">
                    <span class="search">状态</span>
                    <select name="state" id="state" class="input" onchange="changesearch()" style="width:70px; line-height:17px;display:inline-block">
                        <option value="">选择</option>
                        <option value="1">可用</option>
                        <option value="0">不可用</option>
                    </select>
                </li>
                <li style="float: right;"><span class="search">搜索:</span></li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th><input type="checkbox" id="cbTrigger"/></th>
                <th>序号</th>
                <th>名称</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>更新时间</th>
                <th>操作</th>
            </tr>
            <volist name="list" id="vo">
                <c:forEach items="${pageBean.result}" var="payMethod" varStatus="vs">
                    <tr>
                        <td style="text-align:left; padding-left:20px;">
                            <input type="checkbox" name="idCB" value="${payMethod.id}">
                        </td>
                        <td>${vs.count}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/manager/payMethod/details?id=${payMethod.id}">${payMethod.name}</a>
                        </td>
                        <td>
                            <c:if test="${payMethod.state == 1}">
                                <a href="javascript:void(0);" onclick="changeState(${payMethod.id},${payMethod.state})" style="border: 1px solid cornflowerblue;padding: 2px 2px;background-color: #03f5fd">可用</a>
                            </c:if>
                            <c:if test="${payMethod.state != 1}">
                                <a href="javascript:void(0);" onclick="changeState(${payMethod.id},${payMethod.state})" style="border: 1px solid gainsboro;padding: 2px 2px;background-color: gray">不可用</a>
                            </c:if>
                        </td>
                        <td>${fn:replace(payMethod.createTime, 'T', ' ')}</td>
                        <td>${fn:replace(payMethod.updateTime, 'T', ' ')}</td>
                        <td>
                            <div class="button-group">
                                <a class="button border-main" href="${pageContext.request.contextPath}/manager/payMethod/details?id=${payMethod.id}"><span class="icon-edit"></span> 详情</a>
                                <a class="button border-main" href="${pageContext.request.contextPath}/manager/payMethod/openAddUpdate?id=${payMethod.id}"><span class="icon-edit"></span> 修改</a>
                                <a class="button border-red" href="javascript:void(0)" onclick="deleteById(${payMethod.id})"><span class="icon-trash-o"></span> 删除</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8">
                        <%--指定引入页面--%>
                        <%@include file="_page.jsp" %>
                    </td>
                </tr>
            </volist>
        </table>
    </div>
</form>
<script type="text/javascript">
    $(function () {
        //查询条件数据回显
        $("#state option[value=${payMethodQuery.state}]").attr("selected", true);
        $("#keywords").val('${payMethodQuery.name}')

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //单个删除
    function deleteById(id) {
        if (confirm("您确定要删除吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/payMethod/deleteById?id=" + id;
        }
    }

    //搜索
    function changesearch() {
        let stateValue = $("#state").val();
        let keywordsValue = $("#keywords").val();
        let param = "state=" + stateValue + "&keywords=" + keywordsValue;
        document.location.href = "${pageContext.request.contextPath}/manager/payMethod/list?" + param;
    }

    //修改状态
    function changeState(id, state) {
        if (confirm("您确定要修改吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/payMethod/changeState?id=" + id + "&state=" + state;
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
                document.location.href = "${pageContext.request.contextPath}/manager/payMethod/batchDelete?ids=" + ids;
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