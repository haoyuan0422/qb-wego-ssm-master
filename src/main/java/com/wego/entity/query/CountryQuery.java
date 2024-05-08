package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 县区查询类
 * @author: hc
 * @date: 2023/7/2
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryQuery extends BaseQuery {
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
    private Long cityId;
    /**
     * 省名
     */
    private String cityName;

    /**
     * 状态：0不可用 1可用
     */
    private Integer state;

    @Override
    public String toString() {
        return "ProvinceQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provinceId='" + cityId + '\'' +
                ", provinceName='" + cityName + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}