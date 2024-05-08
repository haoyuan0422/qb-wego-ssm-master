package com.wego.converter;

import com.wego.entity.domain.Cart;
import com.wego.entity.vo.CartVO;
import org.mapstruct.Mapper;

/**
 * 购物车转换器
 * @author hc
 */
@Mapper(componentModel = "spring")
public interface CartConverter {
    CartVO cart2CartVO(Cart cart);
}
