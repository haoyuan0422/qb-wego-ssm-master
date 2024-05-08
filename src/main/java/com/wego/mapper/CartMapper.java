package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Cart;
import com.wego.entity.query.CartQuery;

import java.util.List;

/**
 * 购物车Mapper接口
 *  @author hc
 */
public interface CartMapper extends BaseMapper<Cart> {
    List<Cart> selectList(CartQuery cartQuery);

}