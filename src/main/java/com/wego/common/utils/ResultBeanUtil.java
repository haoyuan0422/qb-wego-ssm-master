package com.wego.common.utils;

import com.wego.common.bean.ResultBean;

/**
 * 结果工具类
 * @author hc
 */
public class ResultBeanUtil {
    public static <T> ResultBean<T> success(Integer code, String msg, T data) {
        return new ResultBean<>(code, msg, data);
    }

    public static ResultBean success(Integer code, String msg) {
        return success(code, msg, null);
    }

    public static ResultBean<String> success(String data) {
        return success(200, "成功！", data);
    }

    public static ResultBean success() {
        return success(200, "成功！", null);
    }

    public static <T> ResultBean<T> failure(Integer code, String msg, T data) {
        return new ResultBean<>(code, msg, data);
    }

    public static ResultBean failure(Integer code, String msg) {
        return success(code, msg, null);
    }

    public static ResultBean<String> failure(String data) {
        return success(400, "失败！", data);
    }

    public static ResultBean failure() {
        return success(400, "失败！", null);
    }
}
