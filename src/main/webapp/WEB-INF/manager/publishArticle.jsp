<%--
  Created by IntelliJ IDEA.
  User: Wei
  Date: 2024-01-31
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdn.bootcss.com/jquery/2.0.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor.all.js"></script>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta http-equiv="content-type" content="text/html;charest=UTF-8">
    <title></title>
    <style></style>
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
</head>
<body>
<div class="video-bg">
    <video width="320" height="240" autoplay loop muted>
        <source src="${pageContext.request.contextPath}/static/images/js13.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>
</div>
<div class="dark-light">
    <svg viewBox="0 0 24 24" stroke="currentColor" stroke-width="1.5" fill="none" stroke-linecap="round" stroke-linejoin="round">
        <path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z" />
    </svg>
</div>
<div class="app">
<%--    <div class="header">--%>
<%--        <div class="menu-circle"></div>--%>
<%--        <div class="header-menu">--%>
<%--            <a class="menu-link " href="../index.jsp">虹圈</a>--%>
<%--        </div>--%>
<%--        <div class="header-profile">--%>
<%--            <a href="/userMessage"><img id="imgUser" class="profile-img" src="../${user.headerUrl}" alt=""></a>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="wrapper">
        <div class="main-container">
<%--            <div class="main-header">--%>
<%--                <a class="menu-link-main" href="#">发布</a>--%>
<%--            </div>--%>
            <div class="content-wrapper">
                <div class="content-section">
                    <div class="content-section-title"><h2>文章标题:</h2><input type="text" id="articleTitle" class="adobe-title"></div>
                    <div class="apps-card">
                        <div class="app-card">
                            <script id="articleContent" name="content" type="text/plain"></script>
                            <!-- 实例化编辑器 -->
                            <script type="text/javascript">
                                var ue = UE.getEditor('articleContent',{
                                    //设置宽度
                                    initialFrameWidth:1250,
                                    //设置高度
                                    initialFrameHeight:380
                                });
                            </script>
                            <%--                            <textarea id="articleContent" class="adobe-text" cols="120" rows="18" ></textarea>--%>
                            <div class="app-card-buttons-co">
                                <button class="content-button" onclick="publish()">发布</button>
                            </div>
                            <input type="hidden" id="userId" value="${user.id}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="overlay-app"></div>
</div>
<script>
    $(function () {
        $(".menu-link").click(function () {
            $(".menu-link").removeClass("is-active");
            $(this).addClass("is-active");
        });
    });

    $(function () {
        $(".main-header-link").click(function () {
            $(".main-header-link").removeClass("is-active");
            $(this).addClass("is-active");
        });
    });

    const dropdowns = document.querySelectorAll(".dropdown");
    dropdowns.forEach((dropdown) => {
        dropdown.addEventListener("click", (e) => {
            e.stopPropagation();
            dropdowns.forEach((c) => c.classList.remove("is-active"));
            dropdown.classList.add("is-active");
        });
    });

    $(".search-bar input")
        .focus(function () {
            $(".header").addClass("wide");
        })
        .blur(function () {
            $(".header").removeClass("wide");
        });

    $(document).click(function (e) {
        var container = $(".status-button");
        var dd = $(".dropdown");
        if (!container.is(e.target) && container.has(e.target).length === 0) {
            dd.removeClass("is-active");
        }
    });

    $(function () {
        $(".dropdown").on("click", function (e) {
            $(".content-wrapper").addClass("overlay");
            e.stopPropagation();
        });
        $(document).on("click", function (e) {
            if ($(e.target).is(".dropdown") === false) {
                $(".content-wrapper").removeClass("overlay");
            }
        });
    });

    $(function () {
        $(".status-button:not(.open)").on("click", function (e) {
            $(".overlay-app").addClass("is-active");
        });
        $(".pop-up .close").click(function () {
            $(".overlay-app").removeClass("is-active");
        });
    });

    $(".status-button:not(.open)").click(function () {
        $(".pop-up").addClass("visible");
    });

    $(".pop-up .close").click(function () {
        $(".pop-up").removeClass("visible");
    });

    const toggleButton = document.querySelector('.dark-light');

    toggleButton.addEventListener('click', () => {
        document.body.classList.toggle('light-mode');
    });
    function publish(){
        var articleTitle = $("#articleTitle").val();
        var articleContent = ue.getAllHtml();
        var articleContent1 = ue.getPlainTxt();
        console.log(articleTitle);
        console.log(articleContent1);
        if(check(articleTitle)||check(articleContent1)||articleTitle===""||articleContent1===""){
            alert("请输入文章内容");
            return;
        }
        console.log(articleContent);
        var articleUserId = $("#userId").val();
        $.ajax({
            url:"/publish",
            type:"post",
            data:{
                articleUserId:articleUserId,
                articleTitle:articleTitle,
                articleContent:articleContent,
            },
            complete: function(response) {
                console.log(response.responseText);
                alert(response.responseText);
                document.getElementById("articleTitle").value ="";
                document.getElementById("articleContent").value ="";
            }
        })
    }
    function check(s) {
        var str = /(^\s+)$/;
        console.log(str.test(s));
        return  str.test(s);
    }
</script>
</body>
</html>

