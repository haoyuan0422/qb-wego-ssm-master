package com.wego.entity.domain;

import lombok.AllArgsConstructor;
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
 * 购物项实体类
 *  @author hc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cart_item")
public class CartItem implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品编号
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 个数
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 每一项的总金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 购物车编号
     */
    @Column(name = "cart_id")
    private Long cartId;

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