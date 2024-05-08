package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.OrderComment;
import com.wego.entity.query.OrderCommentQuery;
import com.wego.mapper.OrderCommentMapper;
import com.wego.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单评论服务实现类
 *  @author hc
 */
@Service
public class OrderCommentServiceImpl extends BaseServiceImpl<OrderComment> implements OrderCommentService {
    @Autowired
    private OrderCommentMapper orderCommentMapper;

    @Override
    public PageBean<OrderComment> selectPage(OrderCommentQuery orderCommentQuery) {
        if (orderCommentQuery == null) {
            orderCommentQuery = new OrderCommentQuery();
        }
        //设置分页信息
        Page<OrderComment> page = PageHelper.startPage(orderCommentQuery.getPageNum(), orderCommentQuery.getPageSize());
        //查询数据
        orderCommentMapper.selectList(orderCommentQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<OrderComment> pageBean = PageBeanUtil.page2PageBean(page);

        return pageBean;
    }

}
