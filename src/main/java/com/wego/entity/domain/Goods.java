package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Goods implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
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
     * 显示优先级
     */
    private Integer priority;

    /**
     * 类别编号
     */
    private Long categoryId;

    /**
     * 商品规格
     */
    private String specs;

    /**
     * 介绍
     */
    private String info;

    /**
     * 状态:   0下架  1上架 
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 获取会员价格
     * @return
     */
    public BigDecimal getMemberPrice(){
        return  this.price2;
    }
}