package com.wego.mapper;

import com.wego.entity.domain.Goods;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/6
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("/spring-mybatis.xml")
class GoodsMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;


    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
        final Goods goods = Goods.builder()
                .price1(BigDecimal.ONE)
                .price2(BigDecimal.TEN)
                .unit("套")
                .name("ab")
                .specs("")
                .build();
        final int res = goodsMapper.insert(goods);
        System.out.println(res);
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
        final Goods goods = goodsMapper.selectByPrimaryKey(25L);
        System.out.println(goods);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void selectList() {
        final List<Goods> goodsList = goodsMapper.selectList(null);
        goodsList.forEach(System.out::println);
    }
}