package com.wego.service.impl;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Order;
import com.wego.entity.query.OrderQuery;
import com.wego.service.OrderService;
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
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void selectPage() {
        final PageBean<Order> pageBean = orderService.selectPage(OrderQuery.builder()
                .userIds(List.of(1L, 4L, 5L))
                .build());
        System.out.println(pageBean);
    }
}