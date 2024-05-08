package com.wego.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装Controller传递到前台页面的提示信息
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    /**
     * 通知是否显示
     */
    private Boolean flag;
    /**
     * 通知具体提示信息
     */
    private String msg;
}
