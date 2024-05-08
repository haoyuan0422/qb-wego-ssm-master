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
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/category/doAddUpdate" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${category.id}">
            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${category.name}" name="name" data-validate="required:请输入省份名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>商品种类图片：</label>
                </div>
                <div class="field">
                    <input type="file" class="input w50" value="${category.icon}" name="iconFile"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>优先级：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${category.priority}" name="priority" data-validate="required:请输入优先级"/>
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
    document.getElementById('file').addEventListener('change', function (event) {
        var fileInput = event.target;

        if (fileInput.files && fileInput.files[0]) {
            var file = fileInput.files[0];
            var fileName = file.name;

            document.getElementById('filename').value = fileName;
            // 这里可以进一步处理文件名，比如将其显示在页面上或进行其他操作
        }
    });
</script>
</body>
</html>