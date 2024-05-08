package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 省份查询类
 * @author: hc
 * @date: 2023/7/2
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceQuery extends BaseQuery {
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
     * 状态：0不可用 1可用
     */
    private Integer state;

    @Override
    public String toString() {
        return "ProvinceQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
