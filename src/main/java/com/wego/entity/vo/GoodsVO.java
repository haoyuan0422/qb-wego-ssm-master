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
 * 商品后台列表视图对象
 * @author: hc
 * @date: 2023/7/6
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO {
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

    /**
     * 状态:   0下架  1上架
     */
    private Integer state;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}