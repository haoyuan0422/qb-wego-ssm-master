package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class OrderVO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 购买商品数量
     */
    private Integer amount;

    /**
     * 订单总金额
     */
    private BigDecimal money;

    /**
     * 支付方式编号：1微信 2支付宝 3银联  4线下
     */
    private Integer payMethodId;

    /**
     * 支付方式
     */
    private String payMethodName;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 收件地址编号
     */
    private Long addressId;

    /**
     *收件地址
     */
    private String addressName;

    /**
     * 订单最后状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
