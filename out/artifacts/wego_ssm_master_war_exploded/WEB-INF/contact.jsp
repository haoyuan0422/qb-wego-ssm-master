<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <title>微购商城</title>
    <link href="${pageContext.request.contextPath}/static/css/css.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/js.js" type="text/javascript"></script>
</head>

<body>
<div class="mianCont">
    <div class="top">
        <span>您好！欢迎来到微购商城 请&nbsp;<a href="login.jsp">[登录]</a>&nbsp;<a href="register.html">[注册]</a></span>
        <span class="topRight">
    <a href="vip.html">我的微购</a>&nbsp;|
    <a href="order.html">我的订单</a>&nbsp;| 
    <a href="login.jsp">会员中心</a>&nbsp;|
    <a href="contact.jsp">联系我们</a>
   </span>
    </div>
    <div class="lsg">
        <h1 class="logo"><a href="index.jsp"><img height="90" src="images/logo.png" width="217"/></a></h1>
        <form action="#" class="subBox" method="get">
            <div class="subBoxDiv">
                <input class="subLeft" type="text"/>
                <input class="sub" height="34" src="images/subimg.png" type="image" width="63"/>
                <div class="hotWord">
                    热门搜索：
                    <a href="goods_details.jsp">冷饮杯</a>&nbsp;
                    <a href="goods_details.jsp">热饮杯</a> &nbsp;
                    <a href="goods_details.jsp">纸杯</a>&nbsp;
                    <a href="goods_details.jsp">纸巾</a> &nbsp;
                    <a href="goods_details.jsp">纸巾</a> &nbsp;
                    <a href="goods_details.jsp">纸杯</a>&nbsp;
                </div>
            </div>
        </form>
        <div class="gouwuche">
            <div class="gouCar">
                <img height="20" src="images/gouimg.png" style="position:relative;top:6px;" width="19"/>&nbsp;|&nbsp;
                <strong class="red">0</strong>&nbsp;件&nbsp;|
                <strong class="red">￥ 0.00</strong>
                <a href="order.html">去结算</a> <img height="8" src="images/youjian.jpg" width="5"/>
            </div>
        </div>
    </div>
    <div class="pnt">
        <div class="pntLeft">
            <h2 class="Title">所有商品分类
                <ul class="InPorNav">
                    <li><a href="goods_list.jsp">纸杯系列</a>
                        <div class="chilInPorNav">
                            <a href="#">单层热饮杯</a>
                            <a href="#">双层中空杯</a>
                            <a href="#">瓦楞隔热杯</a>
                            <a href="#">双淋膜冷饮杯</a>
                            <a href="#">爆米花桶</a>
                            <a href="#">纸碗</a>
                            <a href="#">冰淇淋纸杯</a>
                            <a href="#">PS杯盖</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">PET系列</a>
                        <div class="chilInPorNav">
                            <a href="#">PET透明杯</a>
                            <a href="#">PET透明盖</a>
                            <a href="#">PET透明沙拉盒</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">饮品杯配套系列</a>
                        <div class="chilInPorNav">
                            <a href="#">杯袖</a>
                            <a href="#">环保纸浆杯托</a>
                            <a href="#">纸杯打包袋</a>
                            <a href="#">吸管</a>
                            <a href="#">搅拌棒</a>
                            <a href="#">杯盖</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">纸浆环保餐具系列</a>
                        <div class="chilInPorNav">
                            <a href="#">纸碟</a>
                            <a href="#">纸碗</a>
                            <a href="#">纸浆带盖汤碗</a>
                            <a href="#">连体餐盒</a>
                            <a href="#">分体餐盒</a>
                            <a href="#">纸浆沙拉盒</a>
                            <a href="#">纸托盘</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">纸餐盒系列</a>
                        <div class="chilInPorNav">
                            <a href="#">圆底纸餐盒</a>
                            <a href="#">方底纸餐盒</a>
                            <a href="#">三明治纸盒</a>
                            <a href="#">蛋糕盒</a>
                            <a href="#">其他纸餐盒</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">刀叉勺餐具</a>
                        <div class="chilInPorNav">
                            <a href="#">PS刀叉勺系列</a>
                            <a href="#">PLA刀叉勺系列</a>
                            <a href="#">淀粉刀叉勺系列</a>
                            <a href="#">搅拌棒/吸管</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">生活用纸</a>
                        <div class="chilInPorNav">
                            <a href="#">餐巾纸</a>
                            <a href="#">抽纸</a>
                            <a href="#">卫生纸</a>
                            <a href="#">擦手纸</a>
                            <a href="#">其他纸类</a>
                        </div>
                    </li>
                    <li><a href="goods_list.jsp">纸袋/环保打包袋</a>
                        <div class="chilInPorNav">
                            <a href="#">纸袋</a>
                            <a href="#">环保淀粉塑料袋</a>
                            <a href="#">PLA塑料袋</a>
                            <a href="#">食品袋</a>
                        </div>
                    </li>
                </ul>
            </h2>
        </div>
        <div class="pntRight">
            <ul class="nav">
                <li><a href="index.jsp">商城首页</a></li>
                <li><a href="goods_list.jsp">促销中心</a></li>
                <li><a href="login.jsp">会员中心</a></li>
                <li class="navCur"><a href="help.html">帮助中心</a></li>
                <div class="clears"></div>
                <div class="phone">TEL：13264494458</div>
            </ul>
        </div>
        <div class="clears"></div>
    </div>
    <div class="positions">
        当前位置：<a href="index.jsp">首页</a> &gt; <a class="posCur" href="#">帮助中心</a>
    </div>
    <div class="cont">
        <div class="contLeft" id="contLeft">
            <h3 class="leftTitle">帮助中心</h3>
            <dl class="helpNav">
                <dt class="help4">关于我们</dt>
                <dd><a href="#">微购官网</a></dd>
                <dd><a class="helpCur" href="contact.jsp">联系方式</a></dd>
                <dd><a href="about.html">企业简介</a></dd>
            </dl>
        </div>
        <div class="contRight">
            <h4 class="gouLiu">联系我们</h4>
            <div class="contactBox">
                <ul class="contactLeft">
                    <li><strong>北京微购纸制品有限公司 </strong></li>
                    <li>地址：上海市浦东新区民生路1403号1012室</li>
                    <li>邮箱：sales@chunlv.com</li>
                    <li>网址：http://test.webqin.net/chunlv</li>
                    <li>电话：+86 21 3392 6945 / +86 21 3392 6947</li>
                </ul>
                <!--引用百度地图API-->
                <style type="text/css">
                    html, body {
                        margin: 0;
                        padding: 0;
                    }

                    .iw_poi_title {
                        color: #CC5522;
                        font-size: 14px;
                        font-weight: bold;
                        overflow: hidden;
                        padding-right: 13px;
                        white-space: nowrap
                    }

                    .iw_poi_content {
                        font: 12px arial, sans-serif;
                        overflow: visible;
                        padding-top: 4px;
                        white-space: -moz-pre-wrap;
                        word-wrap: break-word
                    }
                </style>
                <script src="https://api.map.baidu.com/api?key=&v=1.1&services=true" type="text/javascript"></script>
                <!--百度地图容器-->
                <div id="dituContent" style="width:501px;height:361px;border:#ccc solid 1px;"></div>
                <script type="text/javascript">
                    //创建和初始化地图函数：
                    function initMap() {
                        createMap();//创建地图
                        setMapEvent();//设置地图事件
                        addMapControl();//向地图添加控件
                        addMarker();//向地图中添加marker
                    }

                    //创建地图函数：
                    function createMap() {
                        let map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
                        let point = new BMap.Point(121.416056, 31.251181);//定义一个中心点坐标
                        map.centerAndZoom(point, 17);//设定地图的中心点和坐标并将地图显示在地图容器中
                        window.map = map;//将map变量存储在全局
                    }

                    //地图事件设置函数：
                    function setMapEvent() {
                        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
                        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
                        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
                        map.enableKeyboard();//启用键盘上下左右键移动地图
                    }

                    //地图控件添加函数：
                    function addMapControl() {
                        //向地图中添加缩放控件
                        let ctrl_nav = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE});
                        map.addControl(ctrl_nav);
                        //向地图中添加缩略图控件
                        let ctrl_ove = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1});
                        map.addControl(ctrl_ove);
                        //向地图中添加比例尺控件
                        let ctrl_sca = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
                        map.addControl(ctrl_sca);
                    }

                    //标注点数组
                    let markerArr = [{title: "上海市秦王网络科技有限公司", content: "地址：上海市普陀区曹杨路1040弄一号楼中友大厦19楼<br/>电话：113264494458<br/>传真：021-62098853&nbsp;<br/>客服电话：021-62098853<br/>销售邮箱：sales@webqin.net<br/>客服邮箱：kefu@webqin.net&nbsp;<br/>公司网址：http://www.webqin.net", point: "121.415831|31.251281", isOpen: 0, icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5}}
                    ];

                    //创建marker
                    function addMarker() {
                        for (let i = 0; i < markerArr.length; i++) {
                            let json = markerArr[i];
                            let p0 = json.point.split("|")[0];
                            let p1 = json.point.split("|")[1];
                            let point = new BMap.Point(p0, p1);
                            let iconImg = createIcon(json.icon);
                            let marker = new BMap.Marker(point, {icon: iconImg});
                            let iw = createInfoWindow(i);
                            let label = new BMap.Label(json.title, {"offset": new BMap.Size(json.icon.lb - json.icon.x + 10, -20)});
                            marker.setLabel(label);
                            map.addOverlay(marker);
                            label.setStyle({
                                borderColor: "#808080",
                                color: "#333",
                                cursor: "pointer"
                            });

                            (function () {
                                let index = i;
                                let _iw = createInfoWindow(i);
                                let _marker = marker;
                                _marker.addEventListener("click", function () {
                                    this.openInfoWindow(_iw);
                                });
                                _iw.addEventListener("open", function () {
                                    _marker.getLabel().hide();
                                })
                                _iw.addEventListener("close", function () {
                                    _marker.getLabel().show();
                                })
                                label.addEventListener("click", function () {
                                    _marker.openInfoWindow(_iw);
                                })
                                if (!!json.isOpen) {
                                    label.hide();
                                    _marker.openInfoWindow(_iw);
                                }
                            })()
                        }
                    }

                    //创建InfoWindow
                    function createInfoWindow(i) {
                        let json = markerArr[i];
                        let iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>" + json.content + "</div>");
                        return iw;
                    }

                    //创建一个Icon
                    function createIcon(json) {
                        let icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w, json.h), {imageOffset: new BMap.Size(-json.l, -json.t), infoWindowOffset: new BMap.Size(json.lb + 5, 1), offset: new BMap.Size(json.x, json.h)})
                        return icon;
                    }

                    initMap();//创建和初始化地图
                </script>
            </div>
        </div>
        <div class="clears"></div>
    </div>
    <div class="inHelp">
        <div class="inHLeft">
            <h4>帮助中心</h4>
            <ul class="inHeList">
                <li><a href="help.html">购物指南</a></li>
                <li><a href="help.html">支付方式</a></li>
                <li><a href="help.html">售后服务</a></li>
                <li><a href="about.html">企业简介</a></li>
                <div class="clears"></div>
            </ul>
        </div>
        <div class="inHLeft">
            <h4>会员服务</h4>
            <ul class="inHeList">
                <li><a href="register.html">会员注册</a></li>
                <li><a href="login.jsp">会员登录</a></li>
                <li><a href="order.html">购物车</a></li>
                <li><a href="order.html">我的订单</a></li>
                <div class="clears"></div>
            </ul>
        </div>
        <div class="inHLeft">
            <h4>支付方式</h4>
            <ul class="inHeList">
                <li><a href="register.html">货到付款</a></li>
                <li><a href="login.jsp">在线支付</a></li>
                <li><a href="order.html">分期付款</a></li>
                <li><a href="order.html">公司转账</a></li>
                <div class="clears"></div>
            </ul>
        </div>
        <div class="inHRight">
            <h4>免费服务热线</h4>
            <div class="telBox">13264494458</div>
            <div class="telBox">13264494458</div>
        </div>
        <div class="clears"></div>
    </div>
    <div class="footer">
        <p>
            <a href="#">进入微购官网</a>&nbsp;|&nbsp;
            <a href="index.jsp">商城首页</a>&nbsp;|&nbsp;
            <a href="goods_list.jsp">促销中心</a>&nbsp;|&nbsp;
            <a href="order.html">我的订单</a>&nbsp;|&nbsp;
            <a href="new.html">新闻动态</a>&nbsp;|&nbsp;
            <a href="login.jsp">会员中心</a>&nbsp;|&nbsp;
            <a href="help.htmll">帮助中心</a>
        </p>
        <p>
            版权所有：北京微购实业有限公司 版权所有 Copyright 2010-2015 沪ICP备00000000号 <a href="http://www.mycodes.net/" target="_blank">微购商城</a>
        </p>
    </div>
</div>
<a class="backTop" href="#">&nbsp;</a>
</body>
</html>
