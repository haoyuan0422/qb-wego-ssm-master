package com.wego.converter;

import com.wego.entity.domain.GoodsComment;
import com.wego.entity.vo.GoodsCommentVO;
import com.wego.entity.vo.GoodsCommentVOFront;
import org.mapstruct.Mapper;

/**
 * 商品评论转换器
 *
 * @author: hc
 * @date: 2023/7/9
 */
@Mapper(componentModel = "spring")
public interface GoodsCommentConverter {

    GoodsCommentVO goodsComment2GoodsCommentVO(GoodsComment goodsComment);

    GoodsCommentVOFront goodsComment2GoodsCommentVOFront(GoodsComment goodsComment);
}
