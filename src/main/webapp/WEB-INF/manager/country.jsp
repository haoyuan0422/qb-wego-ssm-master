<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <div class="panel-head"><strong class="icon-reorder"> 县区列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li>
                <a class="button border-main icon-plus-square-o"
                   href="${pageContext.request.contextPath}/manager/country/openAddUpdate"> 添加县区</a>
                <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
                <a class="button border-red icon-plus-square-o" href="javascript:void(0)" onclick="batchDelete()">批量删除</a>
                <a class="button border-green icon-plus-square-o" href="${pageContext.request.contextPath}/manager/country/list" target="right">
                    <span class="icon-caret-right"></span>返回首页
                </a>
            </li>
            <li style="float: right;">
                <input type="text" placeholder="请输入搜索名称" name="keywords" id="keywords" class="input"
                       onkeydown="return search(event)" style="width:250px; line-height:17px;display:inline-block"/>
                <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()"> 搜索</a>
            </li>
            <li style="float: right;">
                <select id="state" name="state" class="input" onchange="changesearch()"
                        style="width:80px; line-height:17px;display:inline-block">
                    <option value="">状态</option>
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
            </li>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th style="text-align:left; padding-left:20px;"><input type="checkbox" id="cbTrigger"/></th>
            <th>序号</th>
            <th>名称</th>
            <th>城市</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${pageBean.result}" var="country" varStatus="vs">
                <tr>
                    <td style="text-align:left; padding-left:20px;">
                        <input type="checkbox" name="idCB" value="${country.id}">
                    </td>
                    <td> ${vs.count} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/country/details?id=${country.id}">${country.name}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/country/details?id=${country.cityId}">${country.cityName}</a>
                    </td>
                    <td>
                        <c:if test="${country.state == 1}">
                            <a href="javascript:void(0);" onclick="changeState(${country.id},${country.state})"
                               style="border: 1px solid #1a1b1c;background: #03b6fd;padding: 2px 2px;">可用</a>
                        </c:if>
                        <c:if test="${country.state == 0}">
                            <a href="javascript:void(0);" onclick="changeState(${country.id},${country.state})"
                               style="border: 1px solid #1a1b1c;background: #fd0391;padding: 2px 2px;">不可用</a>
                        </c:if>

                    </td>
                    <td>${fn:replace(country.createTime, 'T', ' ')}</td>
                    <td>${fn:replace(country.updateTime, 'T', ' ')}</td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/country/details?id=${country.id}">
                                <span class="icon-edit"></span> 详情
                            </a>
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/country/openAddUpdate?id=${country.id}">
                                <span class="icon-edit"></span> 修改
                            </a>
                            <a class="button border-red" href="javascript:void(0)" onclick="deleteById(${country.id})">
                                <span class="icon-trash-o"></span> 删除
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="9">
                    <%--指定引入页面--%>
                    <%@include file="_page.jsp" %>
                </td>
            </tr>
        </volist>
    </table>
</div>

<script type="text/javascript">
    $(function () {
        //查询条件数据回显
        $("#state option[value=${countryQuery.state}]").attr("selected", true);
        $("#city option[value='${countryQuery.cityId}']").attr("selected", true);
        $("#keywords").val('${countryQuery.name}')

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //搜索
    function changesearch() {
        let stateValue = $("#state").val();
        let cityValue = $("#city").val();
        let keywordsValue = $("#keywords").val();
        let param = "state=" + stateValue + "&cityId=" + cityValue + "&keywords=" + keywordsValue;
        document.location.href = "${pageContext.request.contextPath}/manager/country/list?" + param;
    }

    <%--//搜索文件输入框回车自动查询--%>
    <%--function search(event) {--%>
    <%--    let keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;--%>
    <%--    //判断是否为回车键--%>
    <%--    if (keyCode == 13) {--%>
    <%--        changesearch();--%>
    <%--    }--%>
    <%--}--%>

    //修改状态
    function changeState(id, state) {
        if (confirm("您确定要修改吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/country/changeState?id=" + id + "&state=" + state;
        }
    }

    //单个删除
    function deleteById(id) {
        if (confirm("您确定要删除吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/country/deleteById?id=" + id;
        }
    }

    //复选框全选or取消全选
    $("#cbTrigger").change(function () {
        if ($("#cbTrigger").is(':checked')) {
            $("input[name='idCB']").prop("checked", true);
        } else {
            $("input[name='idCB']").prop('checked', false);
        }
    })

    //批量删除
    function batchDelete() {
        //获取用户选中的数据的主键的集合
        let ids = "";
        $('input:checkbox[name=idCB]:checked').each(function (i) {
            ids += $(this).val() + ",";
        });
        ids = ids.substring(0, ids.length - 1);

        if (ids != "") {
            let t = confirm("您确认要删除选中的内容吗？");
            if (t) {
                document.location.href = "${pageContext.request.contextPath}/manager/country/batchDelete?ids=" + ids;
                return true;
            }
        } else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }
</script>
</body>
</html>