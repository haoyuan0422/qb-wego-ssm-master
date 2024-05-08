<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <title>微购商城</title>
    <link href="${pageContext.request.contextPath}/static/css/css.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
</head>

<body style=" background:#FFF;">
<div class="loginLogo">
    <div class="logoMid">
        <h1 class="logo" style="height:71px;padding-top:10px;">
            <a href="index.jsp"><img src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/logo.png" style="width: 241px;height: 71px;" alt=""/></a>
        </h1>
        <div class="loginReg">
            还没有会员账号?&nbsp;<a href="register.html">免费注册</a>
        </div>
    </div>
</div>
<div class="loginBox">
    <div class="loginLeft">
        <img src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/logo.png" style="width: 716px;height: 376px;"/>
    </div>
    <form action="${pageContext.request.contextPath}/user/doLogin" class="loginRight" method="post">
        <h2>会员登录</h2>
        <h3>用户名</h3>
        <input class="name" type="text" name="account" value="zhangsan"/>
        <h3>密码</h3>
        <input class="pwd" type="password" name="password" value="123456"/>
        <div class="zhuangtai">
            <input checked="checked" type="checkbox"/> 下次自动登录
        </div>
        <div class="subs">
            <input class="submit" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/sub.jpg" type="image"/>
        </div>
    </form>
    <div class="clears"></div>
</div>
<script type="text/javascript">
    //登录验证
    $('.submit').click(function (event) {
        let name = $('.name').val();
        let pwd = $('.pwd').val();

        let namelen = name.length;
        let pwdlen = pwd.length;
        if (namelen < 6) {
            alert("用户名不能小于六位，请重新输入！")
            return false;
        }

        if (pwdlen < 6) {
            alert("你输入的密码不正确，请重新输入！")
            return false;
        }
    });
</script>
</body>
</html>
