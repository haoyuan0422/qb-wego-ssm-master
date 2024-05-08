<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    <div class="panel-head"><strong class="icon-reorder"> 城市详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th>编号：</th>
            <td>${city.id}</td>
        </tr>
        <tr>
            <th>城市名：</th>
            <td>${city.name}</td>
        </tr>
        <tr>
            <th>所属省id：</th>
            <td>${city.provinceId}</td>
        </tr>
        <tr>
            <th>优先级：</th>
            <td>${city.priority}</td>
        </tr>
        <tr>
            <th>状态：</th>
            <td>${city.state}</td>
        </tr>
        <tr>
            <th>创建时间：</th>
            <td>${fn:replace(city.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th>更新时间：</th>
            <td>${fn:replace(city.updateTime, 'T', ' ')}</td>
        </tr>
    </table>
</div>

</body>
</html>