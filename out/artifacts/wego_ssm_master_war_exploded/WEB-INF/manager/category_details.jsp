<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    <div class="panel-head"><strong class="icon-reorder"> 商品种类详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th style="width: 6%;">编号：</th>
            <td>${category.id}</td>
        </tr>
        <tr>
            <th style="width: 15%;">编号：</th>
            <td>${category.id}</td>
        </tr>
        <tr>
            <th style="width: 6%;">种类名：</th>
            <td>${category.name}</td>
        </tr>
        <tr>
            <th style="width: 6%;">图片：</th>
            <td><img src="http://127.0.0.1:9005/wego_resources_server//category/${category.icon}" alt="" width="70" height="50"/></td>
        </tr>
        <tr>
            <th style="width: 6%;">优先级：</th>
            <td>${category.priority}</td>
        </tr>
        <tr>
            <th style="width: 6%;">状态：</th>
            <td>${category.state}</td>
        </tr>
        <tr>
            <th style="width: 6%;">创建时间：</th>
            <td>${fn:replace(category.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th style="width: 6%;">更新时间：</th>
            <td>${fn:replace(category.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/category/list">返回列表页面</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>