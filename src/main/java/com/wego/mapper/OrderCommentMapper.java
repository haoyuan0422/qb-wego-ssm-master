package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.OrderComment;
import com.wego.entity.query.OrderCommentQuery;

import java.util.List;

/**
 * 评论Mapper接口
 *  @author hc
 */
public interface OrderCommentMapper extends BaseMapper<OrderComment> {

    /**
     * 根据条件查询
     *
     * @param orderCommentQuery
     * @return
     */
    List<OrderComment> selectList(OrderCommentQuery orderCommentQuery);

}