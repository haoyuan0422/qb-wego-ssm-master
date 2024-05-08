package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCommentVO implements Serializable {
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
     * 商品编号
     */
    private Long goodsId;

    /**
     * 商品名字
     */
    private String goodsName;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

