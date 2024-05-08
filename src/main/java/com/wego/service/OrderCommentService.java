package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.OrderComment;
import com.wego.entity.query.OrderCommentQuery;

/**
 * 订单评论服务接口
 * @author hc
 */
public interface OrderCommentService extends BaseService<OrderComment> {

    /**
     * 分页查找
     * @param orderCommentQuery
     * @return
     */
    PageBean<OrderComment> selectPage(OrderCommentQuery orderCommentQuery);

}
