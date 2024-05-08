package com.wego.mapper;

import com.wego.entity.domain.Order;
import com.wego.entity.query.OrderQuery;
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
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void selectList() {
        final List<Order> orderList = orderMapper.selectList(OrderQuery.builder()
                .userName("张")
                .build());
        orderList.forEach(System.out::println);
    }
}