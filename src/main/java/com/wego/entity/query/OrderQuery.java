package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 订单查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 支付方式编号：1微信 2支付宝 3银联  4线下
     */
    private Integer payMethodId;

    /**
     * 用户编号
     */
    private List<Long> userIds;
    private String userName;

    /**
     * 订单最后状态
     */
    private Integer state;

    @Override
    public String toString() {
        return "OrderQuery{" +
                "id=" + id +
                ", payMethodId=" + payMethodId +
                ", userIds=" + userIds +
                ", userName='" + userName + '\'' +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
