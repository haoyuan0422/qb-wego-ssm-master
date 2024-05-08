<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%@include file="common/_top.jsp" %>
    <div class="pnt">
        <div class="pntLeft">
            <h2 class="Title">所有商品分类
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
        当前位置：<a href="index.jsp">首页</a> &gt; <a class="posCur" href="#">促销商品</a> ${aa}
    </div>
    <div class="cont">
        <div class="jilu">
            总计 <span>${pageBean.total}</span> 个记录
        </div>
        <div class="contRight" style="float: left;width: 100%;">
            <div class="floorRight">
                <div class="frProList">
                    <c:forEach items="${pageBean.result}" var="goods">
                        <dl>
                            <dt>
                                <a href="${pageContext.request.contextPath}/goods/details?goodsId=${goods.id}">
                                    <img src="http://127.0.0.1:9005/wego_resources_server/goods/${goods.pic}" style="width:132px;height:129px"/>
                                </a>
                            </dt>
                            <dd>${goods.name} ${goods.sellingPoint}</dd>
                            <dd class="cheng">
                                    ${goods.price1}/${goods.unit}
                                    ${goods.price2}/${goods.unit}
                            </dd>
                        </dl>
                    </c:forEach>
                </div>
            </div>
            <div class="clears"></div>
            <!--分页-->
            <%@include file="common/_page.jsp" %>
        </div>
        <div class="clears"></div>
    </div>

    <%@include file="common/_bottom.jsp" %>
</div>
<a class="backTop" href="#">&nbsp;</a>
</body>
</html>
