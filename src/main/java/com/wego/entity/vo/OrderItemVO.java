package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 订单后台列表视图对象
 *  @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemVO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 该项商品的购买数量
     */
    private Integer amount;

    /**
     * 该项商品总金额
     */
    private BigDecimal money;

    /**
     * 商品编号
     */
    private Long goodsId;

    private String goodsPic;

    private String goodsSellingPoint;

    /**
     * 该商品的购买价格
     */
    private BigDecimal goodsPrice;

    private String goodsUnit;

    /**
     * 商品名字
     */
    private String goodsName;

    /**
     * 订单编号
     */
    private Long orderId;
}
