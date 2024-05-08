package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.CartConverter;
import com.wego.entity.domain.Cart;
import com.wego.entity.domain.User;
import com.wego.entity.vo.CartVO;
import com.wego.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartTrans {
    @Autowired
    private UserService userService;
    
    @Autowired
    private CartConverter cartConverter;

    @Getter
    private TransUtil<Cart, CartVO> instance = new TransUtil<>() {
        @Override
        public CartVO tObj2VObj(Cart cart) {
            CartVO cartVO = cartConverter.cart2CartVO(cart);

            final User user = userService.selectByPrimaryKey(cart.getUserId());
            cartVO.setUsername(user.getNickname());

            return cartVO;
        }
    };
    
}
