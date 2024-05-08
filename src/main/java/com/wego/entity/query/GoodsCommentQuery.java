package com.wego.entity.query;

import com.wego.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品评论实体类
 *
 * @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCommentQuery extends BaseQuery {
    /**
     * 编号
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 星级
     */
    private Integer stars;

    /**
     * 订单编号
     */
    private Long orderCommentId;

    /**
     * 商品编号
     */
    private Long goodsId;

    /**
     * 状态
     */
    private Integer state;
}

