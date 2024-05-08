package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车查询类
 *
 *  @author hc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商品总个数
     */
    private Integer amount;

    /**
     * 总金额
     */
    private BigDecimal money;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}