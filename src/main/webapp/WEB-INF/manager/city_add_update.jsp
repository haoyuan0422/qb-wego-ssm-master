<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/manager/css/admin.css">
    <script src="${pageContext.request.contextPath}/static/manager/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/manager/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>${title}内容</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/city/doAddUpdate">
            <input type="hidden" name="id" value="${city.id}">
            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${city.name}" name="name" data-validate="required:请输入城市名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>城市：</label>
                </div>
                <div class="field">
                    <select id="proinceId" name="provinceId" class="input w50">
                        <c:forEach items="${provinceList}" var="province">
                            <option value="${province.key}">${province.value}</option>
                        </c:forEach>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>优先级：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${city.priority}" name="priority" data-validate="required:请输入优先级"/>
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
        if (${city==null}) {
            $("#statePositive").attr('checked', 'checked');
        } else if (${city.state==1}) {
            $("#statePositive").attr('checked', 'checked');
        } else {
            $("#stateNegative").attr('checked', 'checked');
        }
        $("#area option[value='${city.provinceId}']").attr("selected", true);
    });
</script>
</body>
</html>