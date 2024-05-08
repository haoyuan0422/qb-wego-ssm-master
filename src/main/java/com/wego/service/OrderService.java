package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Order;
import com.wego.entity.query.OrderQuery;

/**
 * 订单服务接口
 *  @author hc
 */
public interface OrderService extends BaseService<Order> {

    /**
     * 分页查找
     *
     * @param orderQuery
     * @return
     */
    PageBean<Order> selectPage(OrderQuery orderQuery);

}

