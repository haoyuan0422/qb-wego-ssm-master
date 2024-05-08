<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="top">
    <span>
        <c:if test="${sessionScope.user ==null}">
            您好！欢迎来到微购商城 请&nbsp;<a href="${pageContext.request.contextPath}/user/openLogin">[登录]</a>&nbsp;
            <a href="${pageContext.request.contextPath}/user/openRegister">[注册]</a>
        </c:if>
        <c:if test="${sessionScope.user !=null}">
            当前用户：${sessionScope.user.nickname} <a href="${pageContext.request.contextPath}/user/doLogout">[登出]</a>&nbsp;
        </c:if>
    </span>
    <span class="topRight">
            <a href="../vip.html">我的微购</a>&nbsp;|
            <a href="${pageContext.request.contextPath}/cart/list">我的购物车</a>&nbsp;|
            <a href=".${pageContext.request.contextPath}/order/list">我的订单</a>&nbsp;|
            <a href="../login.html">会员中心</a>&nbsp;|
            <a href="../contact.jsp">联系我们</a>
       </span>
</div>
<div class="lsg">
    <h1 class="logo">
        <a href="${pageContext.request.contextPath}/index">
            <img style="height: 90px;width: 217px;" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/logo.png"/>
        </a>
    </h1>
    <form action="#" class="subBox" method="get">
        <div class="subBoxDiv">
            <form action="${pageContext.request.contextPath}/goods/list" method="get">
                <input class="subLeft" type="text" name="keywords" id="searchInput" placeholder="   根据商品名称描述搜索"/>
                <input class="sub" height="34" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/subimg.png" type="image" width="63"/>
                <div style="position: absolute; z-index: 10;background: #CCCCCC;border:1px solid royalblue;font-size: 28px;margin-top: 34px;width: 560px;display: none;" id="autoTipDiv">
                </div>
            </form>
            <div class="hotWord">
                热门搜索：
                <c:forEach items="${categoryList}" var="category">
                    <a href="${pageContext.request.contextPath}/goods/list?categoryId=${category.key}">${category.value}</a>&nbsp;
                </c:forEach>
            </div>
        </div>
    </form>
    <div class="gouwuche">
        <div class="gouCar">
            <img height="20" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/gouimg.png" style="position:relative;top:6px;" width="19"/>&nbsp;|&nbsp;
            <strong class="red">0</strong>&nbsp;件&nbsp;|
            <strong class="red">￥ 0.00</strong>
            <a href="../order.html">去结算</a> <img height="8" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/youjian.jpg" width="5"/>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/manager/js/jquery.js"></script>
<script>
    $("#searchInput").bind('keyup click', function (e) {
        $("#autoTipDiv").show();
        let keywords = $("#searchInput").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/autoComplete",
            data: {
                keywords: keywords
            },
            success: function (data) {
                console.log(data)
                $("#autoTipDiv").text("");
                let tmp = "";
                for (var i = 0; i < data.length; i++) {
                    console.log(data[i]);
                    tmp += data[i] + "<br>";
                }
                $("#autoTipDiv").html(tmp);
                $("#autoTipDiv").delay(5000).fadeOut();
            }
        })
    });
</script>