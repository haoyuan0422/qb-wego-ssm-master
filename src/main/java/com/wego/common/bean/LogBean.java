package com.wego.common.bean;

import com.wego.common.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogBean {
    private String who;
    private LocalDateTime time;
    private String ip;
    private String done;
    private String params;
    private Object result;

    @Override
    public String toString() {
        return JsonUtil.obj2String(this);
    }
}
