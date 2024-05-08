package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Cart;
import com.wego.entity.query.CartQuery;

/**
 * 购物车服务接口
 *  @author hc
 */
public interface CartService extends BaseService<Cart> {
    /**
     * 分页查找
     * @param cartQuery
     * @return
     */
    PageBean<Cart> selectPage(CartQuery cartQuery);
}
