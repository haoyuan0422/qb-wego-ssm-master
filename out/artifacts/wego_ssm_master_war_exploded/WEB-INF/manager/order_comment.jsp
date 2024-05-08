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
    <div class="panel-head"><strong class="icon-reorder"> 评论列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li>
                <a class="button border-blue icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导入数据</a>
                <a class="button border-green icon-plus-square-o" href="javascript:void(0)" onclick=" "> 导出数据</a>
                <a class="button border-red icon-plus-square-o" href="javascript:void(0)" onclick="batchDelete()"> 批量删除</a>
            </li>
            <li style="float: right;">
                <input type="text" placeholder="请根据内容关键词搜索" name="keywords" id="keywords" class="input" onkeydown="return search(event)" style="width:250px; line-height:17px;display:inline-block"/>
                <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()"> 搜索</a>
            </li>
            <li style="float: right;">
                <select id="stars" name="stars" class="input" style="width:200px; line-height:17px;" onchange="changesearch()">
                    <option value="">星级</option>
                    <option value="1">⭐</option>
                    <option value="2">⭐⭐</option>
                    <option value="3">⭐⭐⭐</option>
                    <option value="4">⭐⭐⭐⭐</option>
                    <option value="5">⭐⭐⭐⭐⭐</option>
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
            <th>序号</th>
            <th>订单编号</th>
            <th>星级</th>
            <th>用户昵称</th>
            <th style="width: 45%">内容</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${pageBean.result}" var="orderComment" varStatus="vs">
                <tr>
                    <td> ${vs.count} </td>
                    <td> ${orderComment.orderId} </td>
                    <td> ${orderComment.stars} </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/user/details?id=${orderComment.userId}">${orderComment.userName}</a>
                    </td>
                    <td style="width: 20%">
                            ${orderComment.content}
                    </td>
                    <td>
                        <c:if test="${orderComment.state == 1}">
                            <a href="javascript:void(0);" onclick="changeState(${orderComment.id},${orderComment.state})" style="border: 1px solid #1a1b1c;background: #03b6fd;padding: 2px 2px;">可用</a>
                        </c:if>
                        <c:if test="${orderComment.state == 0}">
                            <a href="javascript:void(0);" onclick="changeState(${orderComment.id},${orderComment.state})" style="border: 1px solid #1a1b1c;background: #fd0391;padding: 2px 2px;">不可用</a>
                        </c:if>
                    </td>
                    <td>${fn:replace(orderComment.createTime, 'T', ' ')}</td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main" href="${pageContext.request.contextPath}/manager/orderComment/details?id=${orderComment.id}">
                                <span class="icon-edit"></span> 详情
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
        $("#stars option[value=${orderCommentQuery.stars}]").attr("selected", true);
        $("#state option[value=${orderCommentQuery.state}]").attr("selected", true);
        <%--$("#goodsId option[value='${orderCommentQuery.goodsId}']").attr("selected", true);--%>
        $("#keywords").val('${orderCommentQuery.content}')

        //修改状态or删除时弹出对话框
        if (${notification!= null  && notification.flag}) {
            alert("${notification.msg}");
        }
    });

    //搜索
    function changesearch() {
        // let GoodsIdValue = $("#goodsId").val();
        let stateValue = $("#state").val();
        let starsValue = $("#stars").val();
        let keywords = $("#keywords").val();
        let param = "state=" + stateValue + "&stars=" + starsValue + "&keywords=" + keywords;
        console.log(param);
        document.location.href = "${pageContext.request.contextPath}/manager/orderComment/list?" + param;
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
            document.location.href = "${pageContext.request.contextPath}/manager/orderComment/changeState?id=" + id + "&state=" + state;
        }
    }
</script>
</body>
</html>