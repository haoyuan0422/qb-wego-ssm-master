package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.CartItem;
import com.wego.entity.query.CartItemQuery;

import java.util.List;

/**
 * 购物项Mapper接口
 *  @author hc
 */
public interface CartItemMapper extends BaseMapper<CartItem> {
    List<CartItem> selectList(CartItemQuery cartItemQuery);
}