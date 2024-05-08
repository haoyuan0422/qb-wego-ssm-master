package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 评论查询类
 *  @author hc
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommentQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 星级
     */
    private Integer stars;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 状态
     */
    private Integer state;

    @Override
    public String toString() {
        return "CommentQuery{" +
                "id=" + id +
                ", stars='" + stars +
                ", userId='" + userId +
                ", userName='" + userName + '\'' +
                ", orderId='" + orderId +
                ", state=" + state +
                ", pageNum=" + super.getPageNum() +
                ", pageSize=" + super.getPageSize() +
                '}';
    }
}
