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
    <div class="panel-head"><strong class="icon-reorder"> 评论详情</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th>编号：</th>
            <th style="width: 35%;">内容：</th>
            <th>星级：</th>
            <th>用户id：</th>
            <th>订单id：</th>
            <th>优先级：</th>
            <th>状态：</th>
            <th>创建时间：</th>
        </tr>

        <tr>
            <td>${orderComment.id}</td>
            <td>${orderComment.content}</td>
            <td>${orderComment.stars}</td>
            <td>${orderComment.userId}</td>
            <td>${orderComment.orderId}</td>
            <td>${orderComment.priority}</td>
            <td>${orderComment.state}</td>
            <td>${fn:replace(orderComment.createTime, 'T', ' ')}</td>
        </tr>
    </table>

    <br>
    <table class="table table-hover text-center">
        <tr>
            <th>编号：</th>
            <th>商品名称：</th>
            <th style="width: 30%;">内容：</th>
            <th style="width: 25%;">图片：</th>
            <th>星级：</th>
            <th>状态：</th>
            <th>创建时间：</th>
        </tr>
        <c:forEach items="${goodsCommentList}" var="goodsComment" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/manager/goods/details?id=${goodsComment.goodsId}">${goodsComment.goodsName}</a>
                <td>${goodsComment.content}</td>
                <td>
                    <c:set var="imgs" value="${fn:split(goodsComment.imgs,',')}"/>
                    <c:forEach items="${imgs}" var="img">
                        <img src="http://127.0.0.1:9005/wego_resources_server/comment/${img}" alt="" style="width: 100px;">
                    </c:forEach>
                </td>
                <td>${goodsComment.stars}</td>
                <td>${goodsComment.state}</td>
                <td>${fn:replace(goodsComment.createTime, 'T', ' ')}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>