package com.wego.service;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.query.GoodsCommentQuery;

import java.util.List;

public interface GoodsCommentService {

    List<GoodsComment> selectList(GoodsCommentQuery goodsCommentQuery);

    /**
     * 根据条件查询
     *
     * @param goodsCommentQuery
     * @return
     */
    PageBean<GoodsComment> selectPage(GoodsCommentQuery goodsCommentQuery);
}
