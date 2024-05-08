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
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/province/doAddUpdate">
            <input type="hidden" name="id" value="${province.id}">
            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${province.name}" name="name" data-validate="required:请输入省份名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>大区：</label>
                </div>
                <div class="field">
                    <select id="area" name="area" class="input w50">
                        <option value="">请选择大区</option>
                        <option value="华北">华北</option>
                        <option value="西北">西北</option>
                        <option value="西南">西南</option>
                        <option value="港澳台">港澳台</option>
                        <option value="东北">东北</option>
                        <option value="华东">华东</option>
                        <option value="华中">华中</option>
                        <option value="华南">华南</option>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>优先级：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${province.priority}" name="priority" data-validate="required:请输入优先级"/>
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
        if (${province==null}) {
            $("#statePositive").attr('checked', 'checked');
        } else if (${province.state==1}) {
            $("#statePositive").attr('checked', 'checked');
        } else {
            $("#stateNegative").attr('checked', 'checked');
        }
        $("#area option[value='${province.area}']").attr("selected", true);
    });
</script>
</body>
</html>