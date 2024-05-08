package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 城市查询类
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityQuery extends BaseQuery {
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

    @Override
    public String toString() {
        return "ProvinceQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}