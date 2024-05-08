package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "tb_goods_comment")
public class GoodsComment implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论图片
     */
    private String imgs;

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
     * 显示优先级
     */
    private Integer priority;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

