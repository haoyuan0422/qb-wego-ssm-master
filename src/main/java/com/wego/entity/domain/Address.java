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
 * 用户地址实体类
 *
 * @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_address")
public class Address implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 收货人姓名
     */
    @Column(name = "receiver")
    private String receiver;

    /**
     * 县区编号
     */
    @Column(name = "country_id")
    private Long countryId;

    /**
     * 具体地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 邮编
     */
    @Column(name = "postcode")
    private String postcode;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 显示优先级
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * 是否是默认
     */
    @Column(name = "mo_ren")
    private Boolean moRen;

    /**
     * 状态：0不可用 1可用
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