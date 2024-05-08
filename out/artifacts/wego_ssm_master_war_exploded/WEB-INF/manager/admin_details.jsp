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
        th {
            width: 8%;
        }

        th, td {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 管理员详细信息</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th>编号：</th>
            <td>${admin.id}</td>
        </tr>
        <tr>
            <th>姓名：</th>
            <td>${admin.nickname}</td>
        </tr>
        <tr>
            <th>登录名：</th>
            <td>${admin.account}</td>
        </tr>

        <tr>
            <th>头像：</th>
            <td>
                <img src="http://127.0.0.1:9005/wego_resources_server/admin/${admin.avatar}" alt="">
            </td>
        </tr>
        <tr>
            <th>手机号：</th>
            <td>${admin.phone}</td>
        </tr>
        <tr>
            <th>邮箱：</th>
            <td>${admin.email}</td>
        </tr>
        <tr>
            <th>QQ：</th>
            <td>${admin.qq}</td>
        </tr>
        <tr>
            <th>微信：</th>
            <td>${admin.wechat}</td>
        </tr>
        <tr>
            <th>简介：</th>
            <td>${admin.intro}</td>
        </tr>
        <tr>
            <th>状态：</th>
            <td>${admin.state}</td>
        </tr>
        <tr>
            <th>创建时间：</th>
            <td>${fn:replace(admin.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th>更新时间：</th>
            <td>${fn:replace(admin.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/admin/list">返回管理员列表页面</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>