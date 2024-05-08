package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.OrderItem;
import com.wego.entity.query.OrderItemQuery;

import java.util.List;

/**
 * 订单项Mapper接口
 * @author hc
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 根据条件查询
     *
     * @param orderItemQuery
     * @return
     */
    List<OrderItem> selectList(OrderItemQuery orderItemQuery);

}