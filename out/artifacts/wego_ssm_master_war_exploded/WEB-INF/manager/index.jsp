<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/admin.css">
    <script src="${pageContext.request.contextPath}/static/manager/js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="http://127.0.0.1:9005/wego_resources_server/sys/backend/user.jpg" class="radius-circle rotate-hover" height="50" alt=""/>后台管理中心</h1>
    </div>
    <div class="head-l">
        <a class="button button-little bg-green" href="" target="_blank">
            <span class="icon-home"></span> 前台首页
        </a> &nbsp;&nbsp;
        <a href="##" class="button button-little bg-blue">
            <span class="icon-wrench"></span> 清除缓存
        </a> &nbsp;&nbsp;
        <a class="button button-little bg-red" href="${pageContext.request.contextPath}/admin/doLogout">
            <span class="icon-power-off"></span> 退出登录
        </a>
    </div>
</div>
<div class="leftnav" id="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
<%--    <h2><span class="icon-user"></span>基本设置</h2>--%>
<%--    <ul style="display:block">--%>
<%--        <li><a href="${pageContext.request.contextPath}/manager/province/list" target="right"><span class="icon-caret-right"></span>省份管理</a></li>--%>
<%--        <li><a href="${pageContext.request.contextPath}/manager/city/list" target="right"><span class="icon-caret-right"></span>城市管理</a></li>--%>
<%--        <li><a href="${pageContext.request.contextPath}/manager/country/list" target="right"><span class="icon-caret-right"></span>县区管理</a></li>--%>
<%--        <li><a href="${pageContext.request.contextPath}/manager/address/list" target="right"><span class="icon-caret-right"></span>地址管理</a></li>--%>
<%--        <li><a href="${pageContext.request.contextPath}/manager/payMethod/list" target="right"><span class="icon-caret-right"></span>支付方式管理</a></li>--%>
<%--    </ul>--%>
    <h2><span class="icon-user"></span>商城数据</h2>
    <ul style="display:block">
        <li><a href="${pageContext.request.contextPath}/manager/carousel/list" target="right"><span class="icon-caret-right"></span>轮播图管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/category/list" target="right"><span class="icon-caret-right"></span>商品类别管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/goods/list" target="right"><span class="icon-caret-right"></span>商品管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/order/list" target="right"><span class="icon-caret-right"></span>订单管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/orderComment/list" target="right"><span class="icon-caret-right"></span>评论管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/cart/list" target="right"><span class="icon-caret-right"></span>购物车管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/admin/list" target="right"><span class="icon-caret-right"></span>管理员管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/user/list" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/article/publish" target="right"><span class="icon-caret-right"></span>产品知识管理</a></li>
    </ul>


    <h2><span class="icon-user"></span>基本设置</h2>
    <ul style="display:block">
        <li><a href="info.jsp" target="right"><span class="icon-caret-right"></span>产品知识</a></li>
        <li><a href="pass.html" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
        <li><a href="page.html" target="right"><span class="icon-caret-right"></span>单页管理</a></li>
        <li><a href="adv.html" target="right"><span class="icon-caret-right"></span>首页轮播</a></li>
        <li><a href="book.html" target="right"><span class="icon-caret-right"></span>留言管理</a></li>
        <li><a href="column.html" target="right"><span class="icon-caret-right"></span>栏目管理</a></li>
    </ul>
<%--    <h2><span class="icon-pencil-square-o"></span>栏目管理</h2>--%>
<%--    <ul>--%>
<%--        <li><a href="list.html" target="right"><span class="icon-caret-right"></span>内容管理</a></li>--%>
<%--        <li><a href="add.html" target="right"><span class="icon-caret-right"></span>添加内容</a></li>--%>
<%--        <li><a href="cate.html" target="right"><span class="icon-caret-right"></span>分类管理</a></li>--%>
<%--    </ul>--%>
</div>
<ul class="bread" id="bread">
    <li><span class="icon-list" onclick="fun()"></span></li>
    <li><a href="##" id="a_leader_txt">平台介绍</a></li>
</ul>
<div class="admin" id="admin">
    <iframe rameborder="0" src="${pageContext.request.contextPath}/welcome.html" name="right" style="width: 100%;height: 100%; overflow-scrolling: auto;"></iframe>
</div>
<script type="text/javascript">
    $(function () {
        if (${notification!= null  && notification.flag}) {
            alert('${notification.msg}');
        }
    });
    //菜单列表单击事件
    $(function () {
        $(".leftnav h2").click(function () {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function () {
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
    //左侧菜单列表是否显示
    let flag = true;

    //控制左侧菜单显示隐藏及右侧大小变化
    function fun() {
        if (flag) {
            $("#leftnav").css("display", "none");
            $("#admin").css("left", 0);
            $("#bread").css("margin-left", 0)
            flag = false;
        } else {
            $("#leftnav").css("display", "block");
            $("#admin").css("left", "180px;");
            $("#bread").css("margin-left", "190px;")
            flag = true;
        }
    }
</script>
</body>
</html>