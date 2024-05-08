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
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>${title}</strong></div>

    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/manager/goods/doAddUpdate" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${goods.id}">

            <div class="form-group">
                <div class="label">
                    <label>名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.name}" name="name" data-validate="required:请输入商品名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品图片：</label>
                </div>
                <div class="field">
                    <input type="file" class="input w50" value="${goods.pic}" name="picFile"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>图集：</label>
                </div>
                <div class="field">
                    <input type="button" value="添加文件" onclick="addFile(this.parentNode)"/> <br/>
                    <div id="file"></div>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>市场价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.price1}" name="price1"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>会员价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.price2}" name="price2"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>单位：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.unit}" name="unit" data-validate="required:请输入商品单位"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>库存：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.storage}" name="storage"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>卖点：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.sellingPoint}" name="sellingPoint" data-validate="required:请输入你想要的卖点"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>类别编号：</label>
                </div>
                <div class="field">
                    <select id="category1" onchange="requestCategory2()">
                        <option value="-1">一级类别</option>
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>

                    <select id="category2" name="categoryId">
                        <option value="-1">二级类别</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品规格：</label>
                </div>
                <textarea name="specs" cols="200" rows="30" id="specsTextArea" title="请将这一段代码拷贝到JSON编辑器进行编辑">
                </textarea>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>优先级：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${goods.priority}" name="priority"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>状态：</label>
                </div>
                <div class="field">
                    <input id="statePositive" name="state" value="1" type="radio">上架
                    <input id="stateNegative" name="state" value="0" type="radio">下架
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品介绍：</label>
                </div>
                <div class="field">
                    <textarea name="info" cols="200" rows="5">
                        ${goods.info}
                    </textarea>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        //查询条件数据回显
        if (${goods==null}) {
            $("#statePositive").attr('checked', 'checked');
        } else if (${goods.state==1}) {
            $("#statePositive").attr('checked', 'checked');
        } else {
            $("#stateNegative").attr('checked', 'checked');
        }

        let specs = JSON.parse('${goods.specs}');
        let json = JSON.stringify(specs, null, "\t");
        // console.log(json)
        $("#specsTextArea").html(json);
    });

    //商品类别二级联动
    function requestCategory2() {
        let pid = $("#category1 option:selected").val();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/manager/category/selectEntryByPid?pid=" + pid,
            success: function (data) {
                $("#category2").empty();
                $.each(data, function (index, item) {
                    $("#category2").append("<option value=" + item.key + ">" + item.value + "</option>");
                })
            }
        })
    }

    //动态添加或减少文件上传控件
    function addFile(event) {
        //创建一个div标签，用以包含一个input标签和删除按钮
        let innerdiv = document.createElement("div");

        //创建一个input标签
        let inputNode = document.createElement("input");
        inputNode.name = "imgsFile";
        inputNode.type = "file";

        //创建一个删除按钮
        let delNode = document.createElement("input");
        delNode.name = "del";
        delNode.type = "button";
        delNode.value = "删除";

        //删除当前删除按钮所在的标签,为此按钮点击事件创建一个处理函数
        delNode.onclick = function d() {
            this.parentNode.parentNode.removeChild(this.parentNode); //删除此div区域
            let fileNodes = document.getElementsByName("fileName");
        };

        innerdiv.appendChild(inputNode);
        innerdiv.appendChild(delNode);

        let div = document.getElementById("file");
        div.appendChild(innerdiv);
    }
</script>
</body>
</html>