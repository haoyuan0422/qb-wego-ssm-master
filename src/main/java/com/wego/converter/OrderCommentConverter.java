package com.wego.converter;

import com.wego.entity.domain.OrderComment;
import com.wego.entity.vo.OrderCommentVO;
import org.mapstruct.Mapper;

/**
 * 购物车转换器
 * @author hc
 */
@Mapper(componentModel = "spring")
public interface OrderCommentConverter {
    OrderCommentVO comment2CommentVO(OrderComment orderComment);
}
