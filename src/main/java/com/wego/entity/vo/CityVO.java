package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 城市后台列表视图对象
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CityVO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 城市名
     */
    private String name;

    /**
     * 省编号
     */
    private Long provinceId;

    /**
     * 省名
     */
    private String provinceName;

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