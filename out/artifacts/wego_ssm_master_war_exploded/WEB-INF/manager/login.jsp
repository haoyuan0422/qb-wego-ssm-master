<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/admin.css">
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="${pageContext.request.contextPath}/admin/doLogin" method="post">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="account" placeholder="登录账号" value="zhangsan" data-validate="required:请填写账号"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="password" placeholder="登录密码" value="zhangsan" data-validate="required:请填写密码"/>
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input input-big" id="verifyCode" name="verifyCode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" onblur="checkVerifyCode()"/>
                                <img src="${pageContext.request.contextPath}/getVerifyCode" alt="验证码" onClick="this.src=this.src+'?'+Math.random();" class="passcode" style="height:43px;width:100px;cursor:pointer;">
                            </div>
                        </div>
                    </div>
                    <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/manager/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/manager/js/pintuer.js"></script>
<script type="text/javascript">
    $(function () {
        //登录失败，弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //校验验证码是否一致
    function checkVerifyCode() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/checkVerifyCode?verifyCode=' + $("#verifyCode").val(),
            dataType: 'json',
            success: function (data) {
                //如果验证码校验出错
                if (data.code != 200) {
                    alert(data.msg);
                }
            }
        })
    }
</script>
</body>
</html>