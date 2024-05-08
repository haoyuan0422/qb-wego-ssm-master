<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <style>
        th, td {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 地址详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th style="width: 6%;">编号：</th>
            <td>${address.id}</td>
        </tr>
        <tr>
            <th style="width: 15%;">用户姓名：</th>
            <td>${address.receiver}</td>
        </tr>
        <tr>
            <th style="width: 6%;">县区：</th>
            <td>${address.countryId}</td>
        </tr>
        <tr>
            <th style="width: 6%;">地址：</th>
            <td>
                ${pccList.get(2).value}-${pccList.get(1).value}-${pccList.get(0).value}-${address.addr}
            </td>
        </tr>
        <tr>
            <th style="width: 6%;">邮编：</th>
            <td>${address.postcode}</td>
        </tr>
        <tr>
            <th style="width: 6%;">电话：</th>
            <td>${address.phone}</td>
        </tr>
        <tr>
            <th style="width: 6%;">用户id：</th>
            <td>${address.userId}</td>
        </tr>
        <tr>
            <th style="width: 6%;">优先级：</th>
            <td>${address.priority}</td>
        </tr>
        <tr>
            <th style="width: 6%;">是否默认：</th>
            <td>${address.moRen}</td>
        </tr>
        <tr>
        <tr>
            <th style="width: 6%;">状态：</th>
            <td>${address.state}</td>
        </tr>
        <tr>
            <th style="width: 6%;">创建时间：</th>
            <td>${fn:replace(address.createTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <th style="width: 6%;">更新时间：</th>
            <td>${fn:replace(address.updateTime, 'T', ' ')}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/address/list">返回省会列表页面</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>