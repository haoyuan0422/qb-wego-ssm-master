package com.wego.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体类
 * @author: hc
 * @date: 2023/6/10
 */
@Getter
@Setter
@ToString
public class UserSessionVO {
    private Long id;
    private String nickname;
    private String avatar;
    private String account;
}
