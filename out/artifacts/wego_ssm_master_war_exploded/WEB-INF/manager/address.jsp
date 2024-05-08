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
    <div class="panel-head"><strong class="icon-reorder"> 地址列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li>
                <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/manager/address/openAddUpdate"> 添加地址</a>
                <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
                <a class="button border-red icon-plus-square-o" href="javascript:void(0)" onclick="batchDelete()"> 批量删除</a>
            </li>
            <li style="float: right;">
                <input type="text" placeholder="请输入收件人姓名或电话或地址" name="keywords" id="keywords" class="input" onkeydown="return search(event)" style="width:250px; line-height:17px;display:inline-block"/>
                <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()"> 搜索</a>
            </li>
            <li style="float: right;">
                <select id="moRen" name="moRen" class="input" onchange="changesearch()" style="width:80px; line-height:17px;display:inline-block">
                    <option value="">是否默认</option>
                    <option value="true">是</option>
                    <option value="false">否</option>
                </select>
            </li>
            <li style="float: right;">
                <select id="state" name="state" class="input" onchange="changesearch()" style="width:80px; line-height:17px;display:inline-block">
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
            <th>收件人</th>
            <th>县区</th>
            <th>地址</th>
            <th>邮编</th>
            <th>电话</th>
            <th>默认</th>
            <th>状态</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${pageBean.result}" var="address" varStatus="vs">
                <tr>
                    <td style="text-align:left; padding-left:20px;">
                        <input type="checkbox" name="idCB" value="${address.id}">
                    </td>
                    <td> ${vs.count} </td>
                    <td>${address.receiver}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/province/details?id=${address.provinceId}">${address.provinceName}</a>-
                        <a href="${pageContext.request.contextPath}/manager/city/details?id=${address.cityId}">${address.cityName}</a>-
                        <a href="${pageContext.request.contextPath}/manager/country/details?id=${address.countryId}">${address.countryName}</a>
                    </td>
                    <td>${address.addr}</td>
                    <td>${address.postcode}</td>
                    <td>${address.phone}</td>
                    <td>
                        <c:if test="${address.moRen=='true'}">是</c:if>
                        <c:if test="${address.moRen=='false'}">否</c:if>
                    </td>
                    <td>
                        <c:if test="${address.state == 1}">
                            <a href="javascript:void(0);" onclick="changeState(${address.id},${address.state})" style="border: 1px solid #1a1b1c;background: #03b6fd;padding: 2px 2px;">可用</a>
                        </c:if>
                        <c:if test="${address.state == 0}">
                            <a href="javascript:void(0);" onclick="changeState(${address.id},${address.state})" style="border: 1px solid #1a1b1c;background: #fd0391;padding: 2px 2px;">不可用</a>
                        </c:if>
                    </td>
                    <td>${fn:replace(address.updateTime, 'T', ' ')}</td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/address/details?id=${address.id}">
                                <span class="icon-edit"></span> 详情
                            </a>
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/address/openAddUpdate?id=${address.id}"><span class="icon-edit"></span> 修改</a>
                            <a class="button border-red" href="javascript:void(0)" onclick="deleteById(${address.id})">
                                <span class="icon-trash-o"></span> 删除
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="13">
                    <%@include file="_page.jsp" %>
                    <%--指定引入页面--%>
                </td>
            </tr>
        </volist>
    </table>
</div>

<script type="text/javascript">
    $(function () {
        //查询条件数据回显
        $("#state option[value=${addressQuery.state}]").attr("selected", true);
        $("#moRen option[value='${addressQuery.moRen}']").attr("selected", true);
        $("#keywords").val('${addressQuery.receiver}')

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //搜索
    function changesearch() {
        let stateValue = $("#state").val();
        let moRenValue = $("#moRen").val();
        let keywordsValue = $("#keywords").val();
        let param = "state=" + stateValue + "&moRen=" + moRenValue + "&keywords=" + keywordsValue;
        document.location.href = "${pageContext.request.contextPath}/manager/address/list?" + param;
    }

    //搜索文件输入框回车自动查询
    function search(event) {
        let keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        //判断是否为回车键
        if (keyCode == 13) {
            changesearch();
        }
    }

    //修改状态
    function changeState(id, state) {
        if (confirm("您确定要修改吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/address/changeState?id=" + id + "&state=" + state;
        }
    }

    //单个删除
    function deleteById(id) {
        if (confirm("您确定要删除吗?")) {
            document.location.href = "${pageContext.request.contextPath}/manager/address/deleteById?id=" + id;
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
                document.location.href = "${pageContext.request.contextPath}/manager/address/batchDelete?ids=" + ids;
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