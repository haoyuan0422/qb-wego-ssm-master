$(function () {
    //.InPorNav li
    $(".InPorNav li").hover(function () {
        $(this).addClass("inProNavStyle");
        $(this).children(".chilInPorNav").stop(true, true).show();
    }, function () {
        $(this).removeClass("inProNavStyle");
        $(this).children(".chilInPorNav").stop(true, true).hide();
    })

    //.floorRight dl
    $(".floorRight .frProList dl,.contRight .frProList dl").hover(function () {
        $(this).stop(true, true).addClass("frListDlstyle");
        $(this).animate({
            opacity: "0.8",
            left: "-4px"
        })
    }, function () {
        $(this).removeClass("frListDlstyle");
        $(this).stop(true, true).animate({
            opacity: "1",
            left: "0px"
        })
    })

    //返回首页代码
    let bodyWidth2 = $($(window)).height() - 200;
    $(window).scroll(function () {
        if ($(this).scrollTop() > bodyWidth2) {
            $('.backTop').fadeIn();
        } else {
            $('.backTop').fadeOut();
        }
    })

    //.Title 下拉产品菜单
    $(".Title").hover(function () {
        $(this).children(".InPorNav").show();
    }, function () {
        $(this).children(".InPorNav").hide();
    })

    //.leftPorNav
    $(".leftPorNav li").hover(function () {
        $(this).children(".chilInPorNav").show();
    }, function () {
        $(this).children(".chilInPorNav").hide();
    })

    //vipNav
    $(".vipNav dt").click(function () {
        $(this).addClass("vipNavStyle").siblings("dt").removeClass("vipNavStyle");
        $(this).next("dd").show().siblings("dd").hide();
    })

    //购买商品数量减1
    $(".jian").click(function () {
        let amount = $(this).next().val();
        if (amount - 1 <= 0) {
            alert("所购商品数量不能小于等于0");
            return;
        }
        $(this).next().val(amount - 1);
    })
    //购买商品数量加1
    $(".jia").click(function () {
        let amount = $(this).prev().val();
        let stock = $(this).next().val();
        if (amount >= stock || amount >= 200) {
            alert("所购商品数量不能大于库存 或 最多只能买200个");
            return;
        }
        $(this).prev().val(1 + Number(amount));
    })
    //复选框全选or取消全选
    $("#cbTrigger").change(function () {
        if ($("#cbTrigger").is(':checked')) {
            $("input[name='idCB']").prop("checked", true);
        } else {
            $("input[name='idCB']").prop('checked', false);
        }
    })
})