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
                <span style="font-weight: bold;">编号：</span>${order.id}
            </td>
            <td>
                <span style="font-weight: bold;">用户姓名：</span>${order.userName}
            </td>
            <td>
                <span style="font-weight: bold;">支付方式：</span>${order.payMethodName}
            </td>
            <td colspan="2">
                <span style="font-weight: bold;">地址：</span>
                ${pccList.get(2).value} - ${pccList.get(1).value} - ${pccList.get(0).value} - ${order.addressName}
            </td>
        </tr>

        <tr>
            <td>
                <span style="font-weight: bold;">创建时间：</span>${fn:replace(order.createTime, 'T', ' ')}
            </td>
            <td>
                <span style="font-weight: bold;">更新时间：</span>${fn:replace(order.updateTime, 'T', ' ')}
            </td>
            <td>
                <span style="font-weight: bold;">数量：</span>${order.amount}
            </td>
            <td>
                <span style="font-weight: bold;">金额：</span>${order.money}
            </td>
            <td>
                <span style="font-weight: bold;">状态：</span>
                <span style="color: #ee3333">${ORDER_STATE_MAP.get(order.state)}</span>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <table class="table table-hover text-left">
                    <tr>
                        <td>序号</td>
                        <td>商品图片</td>
                        <td>商品名称</td>
                        <td>商品卖点</td>
                        <td>价格</td>
                        <td>单位</td>
                        <td>数量</td>
                        <td>金额</td>
                    </tr>
                    <c:forEach items="${orderItemList}" var="orderItem" varStatus="vs">
                        <tr>
                            <td>${vs.count}</td>
                            <td>
                                <img src="http://127.0.0.1:9005/wego_resources_server/goods/${orderItem.goodsPic}" alt="${orderItem.goodsPic}" style="width: 100px;">
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/manager/goods/details?id=${orderItem.goodsId}">${orderItem.goodsName}
                            </td>
                            <td>${orderItem.goodsSellingPoint}</td>
                            <td>${orderItem.goodsPrice}</td>
                            <td>${orderItem.goodsUnit}</td>
                            <td>${orderItem.amount}</td>
                            <td>${orderItem.money}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;" colspan="5">
                <a href="${pageContext.request.contextPath}/manager/order/list">返回订单列表</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>