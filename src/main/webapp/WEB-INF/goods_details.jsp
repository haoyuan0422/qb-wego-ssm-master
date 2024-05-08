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
    <script src="${pageContext.request.contextPath}/static/js/MagicZoom.js" type="text/javascript"></script>
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
        当前位置：<a href="index.jsp">首页</a> &gt; <a class="posCur" href="#">促销商品</a>
    </div>
    <div class="cont">
        <div class="contRight" style="border:0;float: left;width: 100%;">
            <div class="proBox">
                <div id="tsShopContainer">
                    <div id="tsImgS">
                        <a class="MagicZoom" href="http://127.0.0.1:9005/wego_resources_server/goods/${goods.pic}" id="MagicZoom" title="Images">
                            <img src="http://127.0.0.1:9005/wego_resources_server/goods/${goods.pic}" style="height:300px;width:300px;"/>
                        </a>
                    </div>
                    <div id="tsPicContainer">
                        <div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
                        <div id="tsImgSCon">
                            <ul>
                                <c:set var="imgArray" value="${fn:split(goods.imgs,',')}"/>
                                <c:forEach items="${imgArray}" var="img" varStatus="vs">
                                    <c:if test="${vs.index ==0}">
                                        <li class="tsSelectImg" onclick="showPic(0)" rel="MagicZoom">
                                            <img src="http://127.0.0.1:9005/wego_resources_server/goods/${img}" tsImgS="http://127.0.0.1:9005/wego_resources_server/goods/${img}" style="height:42px;width:42px;"/>
                                        </li>
                                    </c:if>
                                    <c:if test="${vs.index !=0}">
                                        <li onclick="showPic(${vs.index})" rel="MagicZoom">
                                            <img src="http://127.0.0.1:9005/wego_resources_server/goods/${img}" tsImgS="http://127.0.0.1:9005/wego_resources_server/goods/${img}" style="height:42px;width:42px;"/>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                        <div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
                    </div>
                    <img alt="Loading..." class="MagicZoomLoading" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/loading.gif" width="16" height="16"/>
                    <script src="${pageContext.request.contextPath}/static/js/ShopShow.js"></script>
                </div>
                <div class="proBoxRight">
                    <h3 class="proTitle">${goods.name}-${goods.sellingPoint}</h3>
                    <div>商品编号：${goods.id}</div>
                    <div>
                        市场价：<strong style="color: #00aaee;margin-right: 12px;">¥${goods.price1}</strong>
                        会员价：<strong class="red">¥${goods.price2}</strong>
                    </div>
                    <div>单位：${goods.unit}</div>
                    <div>库存：<strong class="red">[有货]</strong></div>
                    <c:forEach items="${goods.specList}" var="entry">
                        <span>${entry.key}：</span>
                        <span style="display: inline-block;margin-bottom: 6px;margin-top: 6px;">
                            <c:forEach items="${entry.items}" var="tableColumn" varStatus="vs">
                                <span style="margin-left: 8px;">
                                    <span style="color: cornflowerblue;">${tableColumn.key}</span> ：${tableColumn.value}
                                </span>
                                <c:if test="${vs.count % 3 ==0}">
                                    <div class="clears"></div>
                                </c:if>
                            </c:forEach>
                        </span>
                    </span>
                        <div class="clears"></div>
                    </c:forEach>
                    <div class="shuliang2">
                        数量：<input type="text" value="1" name="amount" id="amount" style="height: 25px;font-size: 18px;">
                    </div>
                    <div class="jiesuan" style="float: left;">
                        <a style="background-color: yellow;" href="javascript:void(0)" onclick="add2Cart()">加入购物车</a>
                        <a class="jie_2" href="order2.jsp">立即购买</a>
                    </div>
                </div>
                <div class="clears"></div>
            </div>

            <ul class="fangyuan">
                <li>商品晒图</li>
                <li>商品描述</li>
                <li>商品参数</li>
                <li>商品评论</li>
                <div class="clears"></div>
            </ul>
            <div class="fangList">
                <%--商品晒图--%>
                <c:set var="imgArray" value="${fn:split(goods.imgs,',' )}"/>
                <c:forEach items="${imgArray}" var="img">
                    <img src="http://127.0.0.1:9005/wego_resources_server/goods/${img}" alt="${img}"> <br> <br>
                </c:forEach>
            </div>
            <div class="fangList">
                <%--商品描述--%>
                <div class="fangPar">
                    ${goods.info}
                </div>
            </div>
            <div class="fangList">
                <%--商品参数--%>
                <c:forEach items="${goods.specList}" var="entry">
                    <div style="font-weight: bolder;">${entry.key}:</div>
                    <c:forEach items="${entry.items}" var="tableColumn">
                        <div style="margin-left: 28px;">
                            <span style="color: cornflowerblue;">${tableColumn.key}</span> ：${tableColumn.value}
                        </div>
                    </c:forEach>
                </c:forEach>
            </div>
            <div class="fangList">
                <%--商品评论--%>
                <c:forEach items="${pageBean.result}" var="comment" varStatus="vs">
                    <span style="font-weight: bolder;color: #4E6EF2;">楼层：</span>
                    <span style="color: red;">${(pageBean.pageNum-1)*pageBean.pageSize+vs.count}</span>

                    <div style="border: #6e6e6e 1px dotted;margin-left: 50px;">
                        <span style="font-weight: bolder">评论人：</span>${comment.username}
                        <span style="font-weight: bolder;margin-left: 19px;">星级：</span>${comment.stars}
                        <span style="font-weight: bolder;margin-left: 19px;">评论时间：</span>${comment.createTime}
                        <div class="clears"></div>
                        <div>
                            <span style="font-weight: bolder;">评论图片：</span>
                            <c:set var="imgArray" value="${fn:split(comment.imgs,',' )}"/>
                            <c:forEach items="${imgArray}" var="img">
                                <img src="http://127.0.0.1:9005/wego_resources_server/comment/${img}" alt="${img}" style="width: 120px;">
                            </c:forEach>
                        </div>
                        <div>
                            <span style="font-weight: bolder;">评论内容：</span>
                                ${comment.content}
                        </div>
                    </div>
                </c:forEach>
                <!--分页-->
                <%@include file="common/_page.jsp" %>
            </div>
        </div>
        <div class="clears"></div>
    </div>

    <%@include file="common/_bottom.jsp" %>
</div>
<a class="backTop" href="#">&nbsp;</a>
<script type="text/javascript">
    //加入购物车
    function  add2Cart(){
        document.location.href="${pageContext.request.contextPath}/cart/add?goodsId=${goods.id}&amount="+$("#amount").val();
    }

    //.rongliang li
    $(".rongliang li").click(function () {
        $(this).addClass("rongStyle").siblings("li").removeClass("rongStyle");
    })
    //.fangLists:first
    $(".fangList:first").show();
    $(".fangyuan li").click(function () {
        $(this).addClass("fangyuanstyle").siblings("li").removeClass("fangyuanstyle");
        let fangyuan = $(this).index();
        $(".fangList").eq(fangyuan).show().siblings(".fangList").hide();
    })
</script>
</body>
</html>
