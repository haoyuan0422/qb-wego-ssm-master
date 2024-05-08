<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>${title}</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/admin/doAddUpdate" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${admin.id}">
            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${admin.nickname}" name="nickname" data-validate="required:请输入管理员名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>账户：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${admin.account}" id="account" name="account" onblur="checkAccount()" data-validate="required:请输入登录名"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>手机号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${admin.phone}" name="phone" data-validate="required:请输入手机号"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品图片：</label>
                </div>
                <div class="field">
                    <input type="file" class="input w50" value="${admin.avatar}" name="avatarFile"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>邮箱：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${admin.email}" name="email" data-validate="required:请输入邮箱"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>QQ：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${admin.qq}" name="qq" data-validate="required:请输入QQ"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>微信：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${admin.wechat}" name="wechat" data-validate="required:请输入微信"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>简介：</label>
                </div>
                <div class="field">
                    <textarea cols="200" rows="4" name="intro">
                        ${admin.intro}
                    </textarea>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>状态：</label>
                </div>
                <div class="field">
                    <input id="statePositive" name="state" value="1" type="radio">是
                    <input id="stateNegative" name="state" value="0" type="radio">否
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //查询条件数据回显
        if (${admin==null}) {
            $("#statePositive").attr('checked', 'checked');
        } else if (${admin.state==1}) {
            $("#statePositive").attr('checked', 'checked');
        } else {
            $("#stateNegative").attr('checked', 'checked');
        }
    });

    //校验用户名是否存在
    function checkAccount() {
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/manager/admin/checkAccount?account=' + $("#account").val(),
            dataType: 'json',
            success: function (data) {
                //console.log(data)
                if (data.flag) {
                    alert(data.msg);
                } else {
                    alert(data.msg)
                }
            }
        })
    }
</script>
</body>
</html>