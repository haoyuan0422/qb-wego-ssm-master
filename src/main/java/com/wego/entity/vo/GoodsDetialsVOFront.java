package com.wego.entity.vo;

import com.wego.utils.goods.SpecsEntry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
/**
 * 前端商品详情
 * @author hc
 */
@Getter
@Setter
@ToString
public class GoodsDetialsVOFront {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 主图
     */
    private String pic;

    /**
     * 图集
     */
    private String imgs;

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
     * 库存
     */
    private Integer storage;

    /**
     * 卖点
     */
    private String sellingPoint;

    /**
     * 商品规格
     */
    private List<SpecsEntry> specList;

    /**
     * 简介
     */
    private String info;
}

