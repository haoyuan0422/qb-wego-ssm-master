package com.wego.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 地址后台列表视图对象
 *  @author hc
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {
    /**
     * 编号
     */
    private Long id;

    /**
     * 收货人姓名
     */
    private String receiver;

    /**
     * 县区编号
     */
    private Long countryId;
    /**
     * 县区名
     */
    private String countryName;
    private Long cityId;
    private String cityName;
    private Long provinceId;
    private String provinceName;

    /**
     * 具体地址
     */
    private String addr;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 是否是默认
     */
    private Boolean moRen;

    /**
     * 状态：0不可用 1可用
     */
    private Integer state;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
