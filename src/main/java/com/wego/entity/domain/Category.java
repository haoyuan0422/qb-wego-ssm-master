package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *商品类别实体类
 * @author: hc
 * @date: 2023/7/8
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_category")
public class Category implements Serializable {
    /**
     * 类别编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 类别名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 类别图片
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 显示优化级
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * 父类别编号
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 备注信息
     */
    @Column(name = "info")
    private String info;

    /**
     * 状态：1上架，0下架
     */
    @Column(name = "`state`")
    private Integer state;

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