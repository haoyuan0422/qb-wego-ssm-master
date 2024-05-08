package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 省份实体类
 * @author: hc
 * @date: 2023/7/2
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Province implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 省名
     */
    private String name;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 显示优先级
     */
    private Integer priority;

    /**
     * 状态：0不可用 1可用
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