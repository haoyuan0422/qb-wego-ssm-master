package com.wego.service.impl;

import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.entity.domain.OrderItem;
import com.wego.entity.query.OrderItemQuery;
import com.wego.mapper.OrderItemMapper;
import com.wego.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单项服务实现类
 *  @author hc
 */
@Service
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem> implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> selectOrderItemByOrderId(Long orderId) {
        OrderItemQuery orderItemQuery = OrderItemQuery.builder().orderId(orderId).build();
        return orderItemMapper.selectList(orderItemQuery);
    }

}
