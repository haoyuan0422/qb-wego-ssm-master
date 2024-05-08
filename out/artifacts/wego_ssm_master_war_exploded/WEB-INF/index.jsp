<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <title>迈尔家居</title>

    <link href="${pageContext.request.contextPath}/static/css/css.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/kinmaxshow/jquery.kinMaxShow-1.1.src.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {
            $("#kinMaxShow").kinMaxShow();
        });
    </script>
</head>

<body>
<div class="mianCont">
    <%@include file="common/_top.jsp" %>
    <div class="pnt">
        <div class="pntLeft">
            <h2 class="Title">导航栏</h2>
            <%@include file="common/_menu.jsp" %>
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
            <div class="banner" style="display: inline-block">
                <div id="kinMaxShow">
                    <c:forEach items="${carouselList}" var="carousel">
                        <div>
                            <a href="${carousel.value}">
                                <img src="http://127.0.0.1:9005/wego_resources_server/carousel/${carousel.key}"/>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="userInfo">
                <img src="http://127.0.0.1:9005/wego_resources_server/user/mm1.jpg" style="width: 80px;height: 80px;margin-top: 10px;margin-left: 60px;" alt=""/>
                <div>你好，魏小心</div>
                <div>购物车：4</div>
                <div>待收货：2</div>
                <div>待发货：1</div>
                <div>待付款：3</div>
                <div>待评价：8</div>
            </div>
        </div>
        <div class="clears"></div>
    </div>

    <div class="floor">
        <h3 class="floorTitle">猜你喜欢</h3>
        <div class="floorRight">
            <div class="frProList">
                <c:forEach items="${pageBean.result}" var="goods">
                    <dl>
                        <dt><a href="${pageContext.request.contextPath}/goods/details?goodsId=${goods.id}"><img src="http://127.0.0.1:9005/wego_resources_server/goods/${goods.pic}" style="width:132px;height:129px"/></a></dt>
                        <dd>${goods.name} ${goods.sellingPoint}</dd>
                        <dd class="cheng">
                            <span style="color: #5f5f5f;text-decoration-line: line-through;">${goods.price1}/${goods.unit}</span>
                            <span style="margin-left: 15px;">${goods.price2}/${goods.unit}</span>
                        </dd>
                    </dl>
                </c:forEach>
                <div class="clears"></div>
            </div>
        </div>
        <div class="clears"></div>

        <!--分页-->
        <%@include file="common/_page.jsp" %>
    </div>

    <%@include file="common/_bottom.jsp" %>
</div>
<a class="backTop" href="#">&nbsp;</a>
</body>
</html>
