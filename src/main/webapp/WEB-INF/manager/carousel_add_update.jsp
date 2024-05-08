<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/carousel/doAddUpdate" enctype="multipart/form-data">

            <input type="hidden" name="id" value="${carousel.id}">
            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${carousel.name}" name="name" data-validate="required:请输入名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>广告图片：</label>
                </div>
                <div class="field">
                    <input type="file" class="input w50" value="${carousel.pic}" name="picFile"/>
                    <div class="tips"></div>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label>url：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${carousel.url}" name="url" data-validate="required:请输入url"/>
                    <div class="tips"></div>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label>备注信息：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${carousel.info}" name="info" data-validate="required:请输入备注信息"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>优先级：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${carousel.priority}" name="priority"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>状态：</label>
                </div>
                <div class="field">
                    <input id="statePositive" name="state" value="1" type="radio">可用
                    <input id="stateNegative" name="state" value="0" type="radio">不可用
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
        if (${carousel==null}) {
            $("#statePositive").attr('checked', 'checked');
        } else if (${carousel.state==1}) {
            $("#statePositive").attr('checked', 'checked');
        } else {
            $("#stateNegative").attr('checked', 'checked');
        }
    });
</script>

</body>
</html>