<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="InPorNav">
    <c:forEach items="${categoryMap}" var="categoryList">
        <li>
            <a href="${categoryList.key.key}">${categoryList.key.value}</a>
            <div class="chilInPorNav">
                <c:forEach items="${categoryList.value}" var="subCategory">
                    <a href="${pageContext.request.contextPath}/goods/list?categoryId=${subCategory.key}">${subCategory.value}</a>
                </c:forEach>
            </div>
        </li>
    </c:forEach>
</ul>