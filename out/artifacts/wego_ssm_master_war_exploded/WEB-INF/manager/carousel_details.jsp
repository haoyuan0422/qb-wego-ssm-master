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
    <div class="panel-head"><strong class="icon-reorder"> 轮播图详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th style="width: 6%;">编号：</th>
            <td>${carousel.id}</td>
        </tr>
        <tr>
            <th style="width: 6%;">名称：</th>
            <td>${carousel.name}</td>
        </tr>
        <tr>
            <th style="width: 10%;">轮播图：</th>
            <td>
                <img src="http://127.0.0.1:9005/wego_resources_server/carousel/${carousel.pic}" alt="${carousel.pic.substring(1)}" style="width: 100px;">
            </td>
        </tr>
        <tr>
            <th style="width: 6%;">url：</th>
            <td>${carousel.url}</td>
        </tr>
        <tr>
            <th style="width: 6%;">备注信息：</th>
            <td>${carousel.info}</td>
        </tr>
        <tr>
            <th style="width: 6%;">优先级：</th>
            <td>${carousel.priority}</td>
        </tr>
        <tr>
            <th style="width: 6%;">状态：</th>
            <td>${carousel.state}</td>
        </tr>
        <tr>
            <th style="width: 6%;">创建时间：</th>
            <td>${fn:replace(carousel.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th style="width: 6%;">更新时间：</th>
            <td>${fn:replace(carousel.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/carousel/list">返回轮播图列表页面</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>