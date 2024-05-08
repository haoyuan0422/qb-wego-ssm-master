<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <title>微购商城</title>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <link href="${pageContext.request.contextPath}/static/css/css.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/js.js" type="text/javascript"></script>
</head>

<body style=" background:#FFF;">
<div class="loginLogo">
    <div class="logoMid">
        <h1 class="logo" style="height:71px;padding-top:10px;">
            <a href="index.jsp"><img height="71" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/logo.png" width="241"/></a>
        </h1>
        <div class="loginReg">
            还没有会员账号?&nbsp;<a href="${pageContext.request.contextPath}/user/openLogin">登录</a>
        </div>
    </div>
</div>
<div class="loginBox">
    <img class="chengnuo" height="393" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/chengnuo.jpg" width="295"/>
    <form action="${pageContext.request.contextPath}/user/doRegister" class="reg" method="post" onsubmit="return formCheck()">
        <div class="regList">
            <label><span class="red">*</span> 账户名</label>
            <input type="text" name="account" id="account" onblur="checkAccount()"/>
            <span style="color:#999;">请输入邮箱/用户名/手机号</span>
        </div>
        <div class="regList">
            <label><span class="red">*</span> 请设置密码</label>
            <input type="password" name="password1" id="password1" value="123456"/>
        </div>
        <div class="regList">
            <label><span class="password">*</span> 请确认密码</label>
            <input type="password" name="password2" id="password2" value="123456"/>
        </div>
        <div class="regList">
            <label><span class="red">*</span> 验证码</label>
            <input class="yanzheng" name="verifyCode" id="verifyCode" type="text" onblur="checkVerifyCode()"/>
            <img src="${pageContext.request.contextPath}/getVerifyCode" alt="验证码" onClick="this.src=this.src+'?'+Math.random();">
        </div>
        <div class="xieyi">
            <input type="checkbox" id="xieYi" checked/> 我已经阅读并同意<a href="#">《微购用户注册协议》</a>
        </div>
        <div class="reg">
            <%--显示校验出错的提示信息--%>
            <div style="color: red"> ${errorMsg}</div>
            <input height="34" src="http://127.0.0.1:9005/wego_resources_server/sys/frontend/reg.jpg" type="image" width="284"/>
        </div>
    </form>
    <div class="clears"></div>
</div>
<script>
    //表单提交之前数据校验
    function formCheck() {
        let account = $("#account").val();
        let verifyCode = $("#verifyCode").val();

        let password1 = $("#password1").val();
        let password2 = $("#password2").val();
        let xieYiState = $("#xieYi").is(":checked");
        if (account.trim().length <= 6) {
            alert("请输入账户，且长度要大于等于6位")
            return false;
        } else if (verifyCode.trim().length == 0) {
            alert("请输入校验")
            return false;
        } else if (password1.trim().length < 6) {
            alert("请输入密码，且长度要大于等于6位")
            return false;
        } else if (password2.trim().length < 6) {
            alert("请输入确认密码，且长度要大于等于6位")
            return false;
        } else if (password1 != password2) {
            alert("两个密码需要相同")
            return false;
        } else if (!xieYiState) {
            alert("请先同意协议")
            return false;
        } else {
            return true;
        }
    }

    //校验用户名是否存在
    function checkAccount() {
        let account = $("#account").val();
        if (account.trim().length <= 6) {
            alert("请输入账户，且长度要大于等于6位")
            return;
        }
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/user/checkAccount?account=' + account,
            dataType: 'json',
            success: function (data) {
                alert(data.msg);
            }
        })
    }

    //校验验证码是否一致
    function checkVerifyCode() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/verifyCode?op=check' + $("#verifyCode").val(),
            dataType: 'json',
            success: function (data) {
                //console.log(data)
                if (!data.flag) {
                    alert(data.msg);
                }
            }
        })
    }
</script>
</body>
</html>
