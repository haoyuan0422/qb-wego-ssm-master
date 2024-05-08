package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 支付方式查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayMethodQuery extends BaseQuery {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 省名
     */
    private String name;


    /**
     * 状态：0不可用 1可用
     */
    private Integer state;

    @Override
    public String toString() {
        return "PayMethodQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
