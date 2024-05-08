package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单项实体类
 *  @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 该项商品的购买数量
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 该项商品总金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 商品编号
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 状态
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