package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 商品查询类
 * @author: hc
 * @date: 2023/7/6
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 价格下限
     */
    private BigDecimal priceLow;
    /**
     * 价格上限
     */
    private BigDecimal priceHigh;

    /**
     * 卖点
     */
    private String sellingPoint;

    /**
     * 类别编号
     */
    private Long categoryId;

    /**
     * 介绍
     */
    private String info;

    /**
     * 状态:   0下架  1上架 
     */
    private Integer state;

    @Override
    public String toString() {
        return "GoodsQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceLow=" + priceLow +
                ", priceHigh=" + priceHigh +
                ", sellingPoint='" + sellingPoint + '\'' +
                ", categoryId=" + categoryId +
                ", info='" + info + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}