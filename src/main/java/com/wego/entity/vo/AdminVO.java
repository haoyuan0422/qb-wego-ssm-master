package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员后台列表视图对象
 * @author: hc
 * @date: 2023/7/7
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_admin")
public class AdminVO {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 登录名
     */
    private String account;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     *  邮箱
     */
    private String email;

    /**
     *  微信
     */
    private String wechat;

    /**
     * 状态
     */
    private Integer state;
}