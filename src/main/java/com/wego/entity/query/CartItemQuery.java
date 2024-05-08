package com.wego.entity.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 购物项查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItemQuery {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 商品编号
     */
    private Long goodsId;

    /**
     * 购物车编号
     */
    private Long cartId;
}
