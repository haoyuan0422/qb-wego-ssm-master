<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2023/7/4
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagelist">
    <c:set var="baseUrl" value="${pageContext.request.contextPath}/${pageBean.url}"/>

    <a href="${baseUrl}&pageNum=1">首页</a>
    <c:if test="${pageBean.pageNum>1}">
        <a href="${baseUrl}&pageNum=${pageBean.pageNum-1}">上一页</a>
    </c:if>
    <c:forEach begin="1" end="${pageBean.pages}" var="index">
        <c:if test="${index==pageBean.pageNum}">
                                <span class="current">
                                    <a href="${baseUrl}&pageNum=${index}">${index}</a>
                                </span>
        </c:if>
        <c:if test="${index!=pageBean.pageNum}">
            <a href="${baseUrl}&pageNum=${index}">${index}</a>
        </c:if>
    </c:forEach>
    <c:if test="${pageBean.pageNum<pageBean.pages}">
        <a href="${baseUrl}&pageNum=${pageBean.pageNum+1}">下一页</a>
    </c:if>
    <a href="${baseUrl}&pageNum=${pageBean.pages}">尾页</a>
</div>
