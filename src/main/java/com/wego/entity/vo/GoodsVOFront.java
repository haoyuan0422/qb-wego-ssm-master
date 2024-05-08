package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *商品实体类
 * @author: hc
 * @date: 2023/7/6
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_goods")
public class GoodsVOFront {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 主图
     */
    private String pic;

    /**
     * 市场价格
     */
    private BigDecimal price1;

    /**
     * 会员价格
     */
    private BigDecimal price2;

    /**
     * 单位
     */
    private String unit;

    /**
     * 卖点
     */
    private String sellingPoint;
}