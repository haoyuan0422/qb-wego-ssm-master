package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 轮播图查询类
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarouselQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 图片名
     */
    private String name;

    /**
     * 备注信息
     */
    private String info;

    /**
     * 状态：0不可用 1可用
     */
    private Integer state;

    @Override
    public String toString() {
        return "CarouselQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}