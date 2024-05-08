package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.entity.domain.OrderItem;

import java.util.List;

/**
 * 订单项服务接口
 *  @author hc
 */
public interface OrderItemService extends BaseService<OrderItem> {

    /**
     * 查询指定订单的订单项
     * @param orderId
     * @return
     */
    List<OrderItem> selectOrderItemByOrderId(Long orderId);

}
