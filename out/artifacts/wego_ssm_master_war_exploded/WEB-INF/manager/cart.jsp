<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <div class="panel-head"><strong class="icon-reorder"> 购物车列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li>
                <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
            </li>
            <li style="float: right;">
                <input type="text" placeholder="请输入用户昵称或账户" name="keywords" id="keywords" class="input" onkeydown="return search(event)" style="width:250px; line-height:17px;display:inline-block"/>
                <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()"> 搜索</a>
            </li>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th>序号</th>
            <th>用户名</th>
            <th>商品数量</th>
            <th>总金额</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${pageBean.result}" var="cart" varStatus="vs">
                <tr>

                    <td>${vs.count}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/user/details?id=${cart.userId}">${cart.username}</a>
                    </td>
                    <td>${cart.amount}</td>
                    <td>${cart.money}</td>
                    <td>${fn:replace(cart.updateTime, 'T', ' ')}</td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/cart/details?cartId=${cart.id}">
                                <span class="icon-edit"></span> 详情
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <%--指定引入页面--%>
                    <%@include file="_page.jsp" %>
                </td>
            </tr>
        </volist>
    </table>
</div>

<script type="text/javascript">
    $(function () {

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //搜索
    function changesearch() {
        let keywordsValue = $("#keywords").val();
        let param = "keywords=" + keywordsValue;
        document.location.href = "${pageContext.request.contextPath}/manager/cart/list?" + param;
    }

    //搜索文件输入框回车自动查询
    function search(event) {
        let keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        //判断是否为回车键
        if (keyCode == 13) {
            changesearch();
        }
    }


    //单个删除
    function deleteById(id) {
        if (confirm("您确定要删除吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/cart/deleteById?id=" + id;
        }
    }
</script>
</body>
</html>