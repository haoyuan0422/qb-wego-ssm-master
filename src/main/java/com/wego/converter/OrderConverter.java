package com.wego.converter;

import com.wego.entity.domain.Order;
import com.wego.entity.vo.OrderVO;
import org.mapstruct.Mapper;

/**
 * 订单转换器
 *  @author hc
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {
    OrderVO order2OrderVO(Order order);
}
