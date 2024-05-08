package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 *  @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_order")
public class Order implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 购买商品数量
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 订单总金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 支付方式编号：1微信 2支付宝 3银联  4线下
     */
    @Column(name = "pay_method_id")
    private Integer payMethodId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收件地址编号
     */
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 显示优化级
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * 订单最后状态
     */
    @Column(name = "`state`")
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}