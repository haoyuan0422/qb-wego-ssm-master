<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <style>
        th, td {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 省份详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th style="width: 6%;">编号：</th>
            <td>${goods.id}</td>
        </tr>
        <tr>
            <th style="width: 15%;">编号：</th>
            <td>${goods.id}</td>
        </tr>
        <tr>
            <th style="width: 6%;">商品名称：</th>
            <td>${goods.name}</td>
        </tr>
        <tr>
            <th style="width: 6%;">主图：</th>
            <td>
                <img src="http://127.0.0.1:9005/wego_resources_server/goods/${goods.pic}" alt="" style="width: 100px;">
            </td>
        </tr>
        <tr>
            <th style="width: 6%;">图集：</th>
            <td>
                <c:set var="imgs" value="${fn:split(goods.imgs,',')}"/>
                <c:forEach items="${imgs}" var="img">
                    <img src="http://127.0.0.1:9005/wego_resources_server/goods/${img}" alt="" style="width: 100px;">
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th style="width: 6%;">市场价格：</th>
            <td>${goods.price1}</td>
        </tr>
        <tr>
            <th style="width: 6%;">会员价格：</th>
            <td>${goods.price2}</td>
        </tr>
        <tr>
            <th style="width: 6%;">单位：</th>
            <td>${goods.unit}</td>
        </tr>
        <tr>
            <th style="width: 6%;">库存：</th>
            <td>${goods.storage}</td>
        </tr>
        <tr>
            <th style="width: 6%;">卖点：</th>
            <td>${goods.sellingPoint}</td>
        </tr>
        <tr>
            <th style="width: 6%;">优先级：</th>
            <td>${goods.priority}</td>
        </tr>
        <tr>
            <th style="width: 6%;">类别编号：</th>
            <td>${goods.categoryId}</td>
        </tr>
        <tr>
            <th style="width: 6%;">商品规格：</th>
            <td><textarea id="specsTd" rows="27" cols="100" disabled></textarea></td>
        </tr>
        <tr>
            <th style="width: 6%;">商品介绍：</th>
            <td>${goods.info}</td>
        </tr>
        <tr>
            <th style="width: 6%;">商品状态：</th>
            <td>${goods.state}</td>
        </tr>
        <tr>
            <th style="width: 6%;">创建时间：</th>
            <td>${fn:replace(goods.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th style="width: 6%;">更新时间：</th>
            <td>${fn:replace(goods.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/goods/list">返回商品列表页面</a>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    $(function () {
        let json = JSON.stringify(${goods.specs}, null, "\t");
        // console.log(json)
        $("#specsTd").html(json);
    })
</script>
</body>
</html>