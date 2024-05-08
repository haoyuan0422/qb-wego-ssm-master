package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品类别查询类
 * @author: hc
 * @date: 2023/7/8
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQuery extends BaseQuery {
    /**
     * 类别编号
     */
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 父类别编号
     */
    private Long pid;

    /**
     * 备注信息
     */
    private String info;

    /**
     * 状态：1上架，0下架
     */
    private Integer state;

    @Override
    public String toString() {
        return "CategoryQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", info='" + info + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}