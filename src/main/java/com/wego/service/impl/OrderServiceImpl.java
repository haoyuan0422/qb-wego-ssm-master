package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Order;
import com.wego.entity.query.OrderQuery;
import com.wego.mapper.OrderMapper;
import com.wego.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现类
 *  @author hc
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageBean<Order> selectPage(OrderQuery orderQuery) {
        if (orderQuery == null) {
            orderQuery = new OrderQuery();
        }
        //设置分页信息
        Page<Order> page = PageHelper.startPage(orderQuery.getPageNum(), orderQuery.getPageSize());
        //查询数据
        orderMapper.selectList(orderQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Order> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

}

