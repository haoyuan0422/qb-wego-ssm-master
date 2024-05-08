package com.wego.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 评论实体类
 * @author: hc
 * @date: 2023/6/10
 */
@Getter
@Setter
@ToString
public class GoodsCommentVOFront {
    private Long id;

    private String content;

    private String imgs;

    private Integer stars;

    private Long userId;

    private String username;

    private LocalDateTime createTime;

}