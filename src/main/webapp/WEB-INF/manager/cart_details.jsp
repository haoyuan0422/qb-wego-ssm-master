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
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 订单详情</strong></div>
    <table class="table table-hover text-left" style="margin-left: 20px;">
        <tr>
            <td>
                <table class="table table-hover text-left">
                    <tr>
                        <td>
                            <span style="font-weight: bold;">编号：</span>${cart.id}
                        </td>
                        <td>
                            <span style="font-weight: bold;">用户名：</span>
                            <a href="${pageContext.request.contextPath}/manager/user/details?id=${cart.userId}">${userName}</a>
                        </td>
                        <td>
                            <span style="font-weight: bold;">总数量：</span>${cart.amount}
                        </td>
                        <td>
                            <span style="font-weight: bold;">总金额：</span>${cart.money}
                        </td>
                        <td>
                            <span style="font-weight: bold;">创建时间：</span>${fn:replace(cart.createTime, 'T', ' ')}
                        </td>
                        <td>
                            <span style="font-weight: bold;">更新时间：</span>${fn:replace(cart.updateTime, 'T', ' ')}
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table class="table table-hover text-left">
                    <tr>
                        <td>序号</td>
                        <td>商品图片</td>
                        <td>商品名称</td>
                        <td>商品卖点</td>
                        <td>市场价-会员价</td>
                        <td>单位</td>
                        <td>数量</td>
                        <td>金额</td>
                        <td>创建时间</td>
                        <td>更新时间</td>
                    </tr>
                    <c:forEach items="${cartItemList}" var="item" varStatus="vs">
                        <tr>
                            <td>${vs.count}</td>
                            <td>
                                <img src="http://127.0.0.1:9005/wego_resources_server/goods/${item.goodsPic}" alt="${item.goodsPic}" style="width: 100px;">
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/manager/goods/details?id=${item.goodsId}">${item.goodsName}
                            </td>
                            <td>
                                    ${item.goodsSellingPoint}
                            </td>
                            <td>
                                    ${item.goodsPrice1}~${item.goodsPrice2}
                            </td>
                            <td>${item.goodsUnit}</td>
                            <td>${item.amount}</td>
                            <td>${item.money}</td>
                            <td>${fn:replace(item.createTime, 'T', ' ')}</td>
                            <td>${fn:replace(item.updateTime, 'T', ' ')}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">
                <a href="${pageContext.request.contextPath}/manager/cart/list">返回购物车列表</a>
            </td>
        </tr>

    </table>
</div>

</body>
</html>