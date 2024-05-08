package com.wego.service.impl;

import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.entity.domain.CartItem;
import com.wego.entity.query.CartItemQuery;
import com.wego.mapper.CartItemMapper;
import com.wego.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物项服务实现类
 *  @author hc
 */
@Service
public class CartItemServiceImpl extends BaseServiceImpl<CartItem> implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<CartItem> selectList(CartItemQuery cartItemQuery) {
        //查询数据
        return cartItemMapper.selectList(cartItemQuery);
    }

}
