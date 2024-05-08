package com.wego.controller;

import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.converter.GoodsConverter;
import com.wego.entity.domain.Carousel;
import com.wego.entity.domain.Goods;
import com.wego.entity.query.GoodsQuery;
import com.wego.entity.vo.GoodsVOFront;
import com.wego.service.CarouselService;
import com.wego.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 前端首页
 * @author: hc
 * @date: 2023/7/13
 */
@Controller
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsConverter goodsConverter;

    @GetMapping("/index")
    public String index(GoodsQuery goodsQuery, Model model) {
        //轮播图
        List<EntryBean<String>> entryBeanList = new ArrayList<>();
        final List<Carousel> carouselList = carouselService.selectList();
        for (Carousel carousel : carouselList) {
            final EntryBean<String> entryBean = new EntryBean<>(carousel.getPic(), carousel.getUrl());
            entryBeanList.add(entryBean);
        }
        model.addAttribute("carouselList", entryBeanList);

        //商品列表
        final PageBean<Goods> goodsVOPageBean = goodsService.selectPage(goodsQuery);
        final PageBean<GoodsVOFront> pageBean = goodsConverter.goodsPageBean2GoodsVOFrontPageBean(goodsVOPageBean);
        pageBean.setUrl("index");
        model.addAttribute("pageBean", pageBean);

        return "index";
    }

    @ResponseBody
    @GetMapping("/autoComplete")
    Set<String> autoComplete(String keywords) {
        Set<String> set = new HashSet<>();
        if (keywords != null && keywords.length() > 0) {
            List<Goods> goodsList = goodsService.selectRecommend(keywords);

            for (Goods goods : goodsList) {
                fun(set, goods.getName(), keywords);
                fun(set, goods.getSellingPoint(), keywords);
                fun(set, goods.getInfo(), keywords);
            }
        }
        return set;

    }

    private void fun(Set<String> set, String str, String keywords) {
        if (str != null && str.contains(keywords)) {
            String tmp = str.substring(str.indexOf(keywords));
            tmp = tmp.substring(0, Math.min(tmp.length(), 5));
            if (tmp != null) {
                set.add(tmp);
            }
        }
    }

}
