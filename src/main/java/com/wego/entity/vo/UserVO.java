package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * 用户后台列表视图对象
 * @author hc
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birth;

    /**
     * 电话
     */
    private String phone;

    /**
     * 账户
     */
    @Column(name = "account")
    private String account;

    /**
     * 状态:  0删除状态  默认正常1
     */
    private Integer state;
}