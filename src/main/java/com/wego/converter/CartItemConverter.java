package com.wego.converter;

import com.wego.entity.domain.CartItem;
import com.wego.entity.vo.CartItemVO;
import org.mapstruct.Mapper;

/**
 * 购物车购物项转换器
 * @author hc
 */
@Mapper(componentModel = "spring")
public interface CartItemConverter {
    CartItemVO cart2CartVO(CartItem cartItem);
}
