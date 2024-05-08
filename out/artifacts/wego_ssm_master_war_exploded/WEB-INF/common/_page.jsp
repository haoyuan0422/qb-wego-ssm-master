<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bk">
    <ul class="pager">
        <c:set var="baseUrl" value="${pageContext.request.contextPath}/${pageBean.url}"/>
        <c:forEach begin="1" end="${pageBean.pages}" var="index">
            <c:if test="${pageBean.pageNum == index}">
                <li><a class="active" href="${baseUrl}&pageNum=${index}">${index}</a></li>
            </c:if>
            <c:if test="${pageBean.pageNum != index}">
                <li><a href="${baseUrl}&pageNum=${index}">${index}</a></li>
            </c:if>
        </c:forEach>
    </ul>
</div>
