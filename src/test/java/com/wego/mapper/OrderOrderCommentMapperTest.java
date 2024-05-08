package com.wego.mapper;

import com.wego.entity.domain.OrderComment;
import com.wego.entity.query.OrderCommentQuery;
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
class OrderOrderCommentMapperTest {

    @Autowired
    private OrderCommentMapper orderCommentMapper;

    @Test
    void selectList() {
        final List<OrderComment> orderCommentList = orderCommentMapper.selectList(OrderCommentQuery.builder()
                .orderId(604573154412593152L)
                .build());
        orderCommentList.forEach(System.out::println);
    }
}