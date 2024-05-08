<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <title>微购商城</title>
    <link href="${pageContext.request.contextPath}/static/css/css.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/js.js" type="text/javascript"></script>
</head>

<body>
<div class="mianCont">
    <%--指定引入页面--%>
    <%@include file="common/_top.jsp" %>
    <div class="pnt">
        <div class="pntLeft">
            <h2 class="Title">所有商品分类
                <%--指定引入页面--%>
                <%@include file="common/_menu.jsp" %>
            </h2>
        </div>
        <div class="pntRight">
            <ul class="nav">
                <li class="navCur"><a href="${pageContext.request.contextPath}/index">网站首页</a></li>
                <li><a href="${pageContext.request.contextPath}/goods/list?categoryId=68">产品中心</a></li>
                <li><a href="${pageContext.request.contextPath}/aboutUs">关于我们</a></li>
                <li><a href="help.html">产品知识</a></li>
                <div class="clears"></div>
                <div class="phone">TEL：13264494458</div>
            </ul>
        </div>
        <div class="clears"></div>
    </div>
    <div class="positions">
        当前位置：<a href="${pageContext.request.contextPath}/index">首页</a> &gt; <a class="posCur" href="#">购物车</a>
    </div>
    <div class="cont">
        <table class="orderList">
            <tr>
                <th style="with:5%;"><input type="checkbox" id="cbTrigger"/></th>
                <th style="with:50%;">商品</th>
                <th style="with:10%;">单价</th>
                <th style="with:15%;">数量</th>
                <th style="with:20%;">总金额</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${sessionScope.cart}" var="tableColumn">
                <tr>
                    <td>
                        <input type="checkbox" name="idCB"/>
                        <input type="hidden" name="id" value="${tableColumn.key.id}">
                    </td>
                    <td>
                        <dl>
                            <dt>
                                <a href="${pageContext.request.contextPath}/goods?op=detailsFront&id=${tableColumn.key.goodsId}">
                                    <img style="width: 85px;height: 85px;" src="http://127.0.0.1:9005/wego_resources_server/goods/${tableColumn.key.goodsPic}"/>
                                </a>
                            </dt>
                            <dd>${tableColumn.key.goodsName}：${tableColumn.key.goodsSellingPoint}</dd>
                            <div class="clears"></div>
                        </dl>
                    </td>
                    <td><strong class="red">
                            ${tableColumn.key.goodsPrice1}</strong>/${tableColumn.key.goodsUnit}
                        -
                            ${tableColumn.key.goodsPrice2}</strong>/${tableColumn.key.goodsUnit}
                    </td>
                    <td>
                        <div class="jia_jian">
                            <img class="jian" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/jian.jpg" style="height: 25px;width: 21px;"/>
                            <input class="shuliang" type="text" value="${tableColumn.value}"/>
                            <img class="jia" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/jia.jpg" style="height: 25px;width: 21px;"/>
                            <input type="hidden" value="8">
                        </div>
                    </td>
                    <td>
                        <strong class="red">￥${tableColumn.key.money}</strong>
                    </td>
                    <td><a class="green" href="#">收藏</a><br/><a class="green" href="#">删除</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <div class="shanchu"><img src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/lajio.jpg"/> 全部删除</div>
                </td>
            </tr>
        </table>
        <div class="zongji">
            总计(不含运费)：<strong class="red">￥${totalMoney}</strong>
        </div>
        <div class="jiesuan">
            <a class="jie_1" href="${pageContext.request.contextPath}/index">继续购物&gt;&gt;</a>
            <a class="jie_2" href="order2.jsp">立即结算&gt;&gt;</a>
            <div class="clears"></div>
        </div>
        <div class="clears"></div>
    </div>

    <%@include file="common/_bottom.jsp" %>
    <%--指定引入页面--%>
</div>
<a class="backTop" href="#">&nbsp;</a>
</body>
</html>
