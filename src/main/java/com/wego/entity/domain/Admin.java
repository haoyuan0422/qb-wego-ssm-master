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
 * 管理员实体类
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
public class Admin implements Serializable {
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
     * 登录密码
     */
    private String password;

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
     *  QQ
     */
    private String qq;

    /**
     *  微信
     */
    private String wechat;

    /**
     *  简介
     */
    private String intro;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}