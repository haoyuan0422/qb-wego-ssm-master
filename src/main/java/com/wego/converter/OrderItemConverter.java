package com.wego.converter;

import com.wego.entity.domain.OrderItem;
import com.wego.entity.vo.OrderItemVO;
import org.mapstruct.Mapper;

/**
 * 订单转换器
 *  @author hc
 */
@Mapper(componentModel = "spring")
public interface OrderItemConverter {
    OrderItemVO orderItem2OrderItemVO(OrderItem orderItem);
}
