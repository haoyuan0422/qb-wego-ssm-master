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
 * 购物车实体类
 *  @author hc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cart")
public class Cart implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 商品总个数
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 总金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 显示优化级
     */
    @Column(name = "priority")
    private Integer priority;

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