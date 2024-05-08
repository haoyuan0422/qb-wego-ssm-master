package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.OrderConverter;
import com.wego.entity.domain.Address;
import com.wego.entity.domain.Order;
import com.wego.entity.domain.PayMethod;
import com.wego.entity.domain.User;
import com.wego.entity.vo.OrderVO;
import com.wego.mapper.AddressMapper;
import com.wego.mapper.PayMethodMapper;
import com.wego.mapper.UserMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderTrans {
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private PayMethodMapper payMethodMapper;

    @Getter
    private TransUtil<Order, OrderVO> instance = new TransUtil<>() {
        @Override
        public OrderVO tObj2VObj(Order order) {
            OrderVO orderVO = orderConverter.order2OrderVO(order);

            final User user = userMapper.selectByPrimaryKey(orderVO.getUserId());
            orderVO.setUserName(user.getNickname());

            final PayMethod payMethod = payMethodMapper.selectByPrimaryKey(orderVO.getPayMethodId());
            orderVO.setPayMethodName(payMethod.getName());

            final Address address = addressMapper.selectByPrimaryKey(orderVO.getAddressId());
            orderVO.setAddressName(address.getAddr());
            return orderVO;
        }
    };
}
