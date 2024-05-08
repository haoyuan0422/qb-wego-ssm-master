package com.wego.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户商品评论实体类
 * @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_order_comment")
public class OrderComment implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 星级
     */
    @Column(name = "stars")
    private Integer stars;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 显示优先级
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * 状态
     */
    @Column(name = "`state`")
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
}