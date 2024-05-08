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
        th, td {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 支付方式详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th style="width: 15%;">编号：</th>
            <td>${payMethod.id}</td>
        </tr>
        <tr>
            <th style="width: 15%;">支付方式名：</th>
            <td>${payMethod.name}</td>
        </tr>
        <tr>
            <th style="width: 15%;">优先级：</th>
            <td>${payMethod.priority}</td>
        </tr>
        <tr>
            <th style="width: 15%;">状态：</th>
            <td>${payMethod.state}</td>
        </tr>
        <tr>
            <th style="width: 15%;">创建时间：</th>
            <td>${fn:replace(payMethod.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th style="width: 15%;">更新时间：</th>
            <td>${fn:replace(payMethod.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/payMethod/list">返回支付方式列表页面</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
