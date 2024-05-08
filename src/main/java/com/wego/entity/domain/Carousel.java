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
import java.time.LocalDateTime;

/**
 * 轮播图实体类
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_carousel")
public class Carousel implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String pic;

    /**
     * 超连接
     */
    private String url;

    /**
     * 备注信息
     */
    private String info;

    /**
     * 显示优先级
     */
    private Integer priority;

    /**
     * 状态  1可用  0不可用
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
}