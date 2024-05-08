package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Order;
import com.wego.entity.query.OrderQuery;

import java.util.List;

/**
 * 订单Mapper接口
 *  @author hc
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 根据条件查询
     *
     * @param orderQuery
     * @return
     */
    List<Order> selectList(OrderQuery orderQuery);
}