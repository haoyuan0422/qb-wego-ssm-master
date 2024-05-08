<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="panel-head"><strong class="icon-reorder"> 用户详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th style="width: 6%;">编号：</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th style="width: 6%;">昵称：</th>
            <td>${user.nickname}</td>
        </tr>
        <tr>
            <th style="width: 6%;">性别：</th>
            <td>${user.gender==1? '男' : '女'}</td>
        </tr>
        <tr>
            <th style="width: 6%;">头像：</th>
            <td>
                <c:if test="${user.avatar != null}">
                    <img src="http://127.0.0.1:9005/wego_resources_server/user/${user.avatar}" style="width: 100px;">
                </c:if>
                <c:if test="${user.avatar == null}">
                    无
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="width: 6%;">生日：</th>
            <td>${user.birth}</td>
        </tr>
        <tr>
            <th style="width: 6%;">优先级：</th>
            <td>${user.priority}</td>
        </tr>
        <tr>
            <th style="width: 6%;">状态：</th>
            <td>${user.state}</td>
        </tr>
        <tr>
            <th style="width: 6%;">创建时间：</th>
            <td>${fn:replace(user.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th style="width: 6%;">更新时间：</th>
            <td>${fn:replace(user.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/user/list">返回用户列表页面</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>