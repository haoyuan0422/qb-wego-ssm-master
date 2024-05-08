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
    <div class="panel-head"><strong class="icon-reorder"> 订单列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li style="float: right;">
                <input type="text" placeholder="请输入搜索用户名称" name="keywords" id="keywords" class="input" onkeydown="return search(event)" style="width:250px; line-height:17px;display:inline-block"/>
                <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()"> 搜索</a>
            </li>
            <li>
                <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
            </li>
            <li style="float: right;">
                <select id="payMethodId" name="payMethodId" class="input" style="width:80px; line-height:17px;" onchange="changesearch()">
                    <option value="">支付方式</option>
                    <option value="1">微信支付</option>
                    <option value="2">支付宝支付</option>
                    <option value="3">银联支付</option>
                    <option value="4">线下支付</option>
                </select>
            </li>
            <li style="float: right;">
                <select id="state" name="state" class="input" onchange="changesearch()" style="width:60px; line-height:17px;display:inline-block">
                    <option value="">状态</option>
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
            </li>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th>序号</th>
            <th>用户</th>
            <th>购买数量</th>
            <th>金额</th>
            <th>支付方式</th>
            <th>地址</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${pageBean.result}" var="order" varStatus="vs">
                <tr>
                    <td> ${vs.count} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/user/details?id=${order.userId}">${order.userName}</a>
                    </td>
                    <td>${order.amount}</td>
                    <td>${order.money}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/payMethod/details?id=${order.payMethodId}">${order.payMethodName}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/address/details?id=${order.addressId}">${order.addressName}</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);" style="border: 1px solid #1a1b1c;background: #03b6fd;padding: 2px 2px;">${ORDER_STATE_MAP.get(order.state)}</a>
                    </td>
                    <td>${fn:replace(order.createTime, 'T', ' ')}</td>
                    <td>${fn:replace(order.updateTime, 'T', ' ')}</td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/order/details?id=${order.id}">
                                <span class="icon-edit"></span> 详情
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="11">
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
        $("#state option[value=${orderQuery.state}]").attr("selected", true);
        $("#area option[value='${orderQuery.payMethodId}']").attr("selected", true);

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //搜索
    function changesearch() {
        let stateValue = $("#state").val();
        let payMethodIdValue = $("#payMethodId").val();
        let keywordsValue = $("#keywords").val();
        let param = "state=" + stateValue + "&payMethodId=" + payMethodIdValue + "&keywords=" + keywordsValue;
        document.location.href = "${pageContext.request.contextPath}/manager/order/list?" + param;
    }

    //搜索文件输入框回车自动查询
    function search(event) {
        let keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        //判断是否为回车键
        if (keyCode == 13) {
            changesearch();
        }
    }
</script>
</body>
</html>