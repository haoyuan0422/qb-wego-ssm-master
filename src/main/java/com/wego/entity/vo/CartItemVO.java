package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物项后台列表视图对象
 *  @author hc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVO {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 商品编号
     */
    private Long goodsId;
    private String goodsPic;
    private String goodsSellingPoint;
    private BigDecimal goodsPrice1;
    private BigDecimal goodsPrice2;
    private String goodsUnit;
    /**
     * 商品名字
     */
    private String goodsName;

    /**
     * 个数
     */
    private Integer amount;

    /**
     * 每一项的总金额
     */
    private BigDecimal money;

    /**
     * 购物车编号
     */
    private Long cartId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}