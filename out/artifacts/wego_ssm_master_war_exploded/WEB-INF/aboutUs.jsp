<%--
  Created by IntelliJ IDEA.
  User: Wei
  Date: 2024-01-30
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>迈尔家居 - 联系我们</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous"><link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link rel="icon" href="http://127.0.0.1:9005/wego_resources_server/sys/favicon.ico" type="image/ico">
<%--    <title>微购商城</title>--%>
    <link href="${pageContext.request.contextPath}/static/css/css.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/js.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {
            $("#kinMaxShow").kinMaxShow();
        });
    </script>
</head>
<body>
<!-- partial:index.partial.html -->
<div class="mianCont">
    <%@include file="common/_top.jsp" %>
    <div class="pnt">
        <div class="pntLeft">
            <h2 class="Title">所有商品分类
                <%@include file="common/_menu.jsp" %>
            </h2>
        </div>
        <div class="pntRight">
            <ul class="nav">
                <li class="navCur"><a href="${pageContext.request.contextPath}/index">网站首页</a></li>
                <li><a href="${pageContext.request.contextPath}/goods/list?categoryId=68">产品中心</a></li>
                <li><a href="${pageContext.request.contextPath}/WEB-INF/aboutUs.jsp">关于我们</a></li>
                <li><a href="help.html">产品知识</a></li>
                <div class="clears"></div>
                <div class="phone">TEL：13264494458</div>
            </ul>
        </div>
        <div class="clears"></div>
    </div>
    <div class="positions">
        当前位置：<a href="index.jsp">首页</a> &gt; <a class="posCur" href="#">促销商品</a>
    </div>
    <section class="contact" id="contact">
        <div class="container">
            <div class="heading text-center">
                <h2>联系
                    <span> 我们 </span></h2>
                <p>吸引你的是品牌,惊艳你的是品质 留下你的是品味,能够长期合作下去的是服务
                    <br>我负责坦诚相待,你负责拭目以待～</p>
            </div>
            <div class="row">
                <div class="col-md-5">
                    <!-- <div class="title">
                        <h3>联系细节</h3>
                        <p>我负责坦诚相待,你负责拭目以待～</p>
                    </div> -->
                    <div class="content">
                        <!-- Info-1 -->
                        <div class="info">
                            <i class="fas fa-mobile-alt"></i>
                            <h4 class="d-inline-block">电话（大兵）:
                                <br>
                                <span>定制批发：18063147064</span><br>
                                <span>工厂合作：13684809607</span>
                            </h4>
                        </div>
                        <!-- Info-2 -->
                        <div class="info">
                            <i class="far fa-envelope"></i>
                            <h4 class="d-inline-block">邮箱:
                                <br>
                                <span>3258615031@qq.com</span></h4>
                        </div>
                        <!-- Info-3 -->
                        <div class="info">
                            <i class="fas fa-map-marker-alt"></i>
                            <h4 class="d-inline-block">地址:<br>
                                <span>广东省广州市白云区嘉禾望岗七星岗路德和国际A406</span></h4>
                        </div>
                        <br>
                        <img style="width: 170px; height: 200px;" src="${pageContext.request.contextPath}/static/images/taobao.jpg" alt="淘宝店铺">
                        <img style="width: 170px; height: 200px;" src="${pageContext.request.contextPath}/static/images/wechat.jpg" alt="微信">
                    </div>
                </div>

                <div class="col-md-7">
                    <img style="width: 750px; height: 420px;" src="${pageContext.request.contextPath}/static/images/brand.jpg" alt="迈尔家居">
                    <!-- <form>
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="Name">
                            </div>
                            <div class="col-sm-6">
                                <input type="email" class="form-control" placeholder="Email">
                            </div>
                            <div class="col-sm-12">
                                <input type="text" class="form-control" placeholder="Subject">
                            </div>
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" rows="5" id="comment" placeholder="Message"></textarea>
                        </div>
                        <button class="btn btn-block" type="submit">Send Now!</button>
                    </form> -->
                </div>
            </div>
        </div>
    </section>
    <%@include file="common/_bottom.jsp" %>
</div>
<a class="backTop" href="#">&nbsp;</a>
<!-- partial -->
</body>
</html>
