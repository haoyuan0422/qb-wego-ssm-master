package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.query.GoodsCommentQuery;

import java.util.List;

/**
 * 评论Mapper接口
 *  @author hc
 */
public interface GoodsCommentMapper extends BaseMapper<GoodsComment> {

    /**
     * 根据条件查询
     *
     * @param goodsCommentQuery
     * @return
     */
    List<GoodsComment> selectList(GoodsCommentQuery goodsCommentQuery);

}