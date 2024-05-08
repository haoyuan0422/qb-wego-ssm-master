package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.entity.domain.CartItem;
import com.wego.entity.query.CartItemQuery;

import java.util.List;

/**
 *  购物项服务接口
 *  @author hc
 */
public interface CartItemService extends BaseService<CartItem> {
    /**
     * 根据条件查询
     * @param cartItemQuery
     * @return
     */
    List<CartItem> selectList(CartItemQuery cartItemQuery);
}
