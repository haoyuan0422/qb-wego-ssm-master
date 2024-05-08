package com.wego.entity.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单项查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 状态
     */
    private Integer state;

}
