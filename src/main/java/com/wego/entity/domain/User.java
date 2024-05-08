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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体
 * @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 性别
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 生日
     */
    @Column(name = "birth")
    private LocalDate birth;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 账户
     */
    @Column(name = "account")
    private String account;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 显示优先级
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * 状态:  0删除状态  默认正常1
     */
    @Column(name = "`state`")
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}