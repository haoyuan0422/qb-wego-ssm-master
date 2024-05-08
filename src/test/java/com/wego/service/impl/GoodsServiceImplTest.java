package com.wego.service.impl;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Goods;
import com.wego.entity.query.GoodsQuery;
import com.wego.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/6
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class GoodsServiceImplTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    void selectTop() {
        final List<Goods> goodsList = goodsService.selectRecommend("标");
        goodsList.forEach(System.out::println);
    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
        final Goods goods = goodsService.selectByPrimaryKey(720L);
        System.out.println(goods);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void selectPage() {
        final PageBean<Goods> goodsPageBean = goodsService.selectPage(GoodsQuery.builder()
                .build());
        System.out.println(goodsPageBean);
    }
}