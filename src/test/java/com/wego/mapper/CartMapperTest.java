package com.wego.mapper;

import com.wego.entity.domain.Cart;
import com.wego.entity.query.CartQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/12
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("/spring-mybatis.xml")
class CartMapperTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    void selectList() {
        final List<Cart> cartList = cartMapper.selectList(CartQuery.builder()
                .userIds(List.of(1L, 4L, 5L))
                .build());
        cartList.forEach(System.out::println);
    }
}