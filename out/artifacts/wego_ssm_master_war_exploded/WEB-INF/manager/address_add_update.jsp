<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/address/doAddUpdate">
            <input type="hidden" name="id" value="${address.id}">
            <div class="form-group">
                <div class="label">
                    <label>用户姓名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${address.receiver}" name="receiver"
                           data-validate="required:请输入用户姓名"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>县区：</label>
                </div>
                <div class="field">
                    <select id="province" onchange="getCityByProvince()">
                        <option value="-1">省份</option>
                        <c:forEach items="${provinceList}" var="province">
                            <option value="${province.id}">${province.name}</option>
                        </c:forEach>
                    </select>

                    <select id="city" name="city" onchange="getCountryByCity()">
                        <option value="-1">城市</option>
                    </select>

                    <select id="country" name="countryId">
                        <option value="-1">县区</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${address.addr}" name="addr" data-validate="required:请输入地址"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>邮编：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${address.postcode}" name="postcode" data-validate="required:请输入邮编"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${address.phone}" name="phone" data-validate="required:请输入电话"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>优先级：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${address.priority}" name="priority" data-validate="required:请输入优先级"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>是否默认：</label>
                </div>
                <div class="field">
                    <select id="moRen" name="moRen" class="input" onchange="changesearch()"
                            style="width:80px; line-height:17px;display:inline-block">
                        <option value="">是否默认</option>
                        <option value="true">是</option>
                        <option value="false">否</option>
                    </select>
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
        if (${address==null}) {
            $("#statePositive").attr('checked', 'checked');
        } else if (${address.state==1}) {
            $("#statePositive").attr('checked', 'checked');
        } else {
            $("#stateNegative").attr('checked', 'checked');
        }
    });
    $(function () {
        //查询条件数据回显
        $("#moRen option[value='${address.moRen}']").attr("selected", true);
        if (${address.id != null}) {
            $("#province option[value='${province.id}']").attr("selected", true);
        }
    });

    //省市县级联中的市
    function getCityByProvince() {
        let provinceId = $("#province option:selected").val();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/manager/city/selectCityByProvinceId?provinceId=" + provinceId,
            success: function (data) {
                $("#city").empty();
                $("#city").append("<option value=" + -1 + ">" + "城市" + "</option>");
                $.each(data, function (index, item) {
                    $("#city").append("<option value=" + item.key + ">" + item.value + "</option>");
                })
                if (${address.id != null}) {
                    $("#city option[value='${city.id}']").attr("selected", true);
                }
            }
        })
    }

    //省市县级联中的县
    function getCountryByCity() {
        let cityId = $("#city option:selected").val();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/manager/country/selectCountryByCityId?cityId=" + cityId,
            success: function (data) {
                $("#country").empty();
                $("#country").append("<option value='-1'>" + "县区" + "</option>");
                $.each(data, function (index, item) {
                    $("#country").append("<option value='" + item.key + "'>" + item.value + "</option>");
                });
                if (${address.id != null}) {
                    $("#country option[value='${country.id}']").attr("selected", true);
                }
            }
        })
    }

</script>
</body>
</html>