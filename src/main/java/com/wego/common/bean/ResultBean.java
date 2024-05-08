package com.wego.common.bean;

import java.io.Serializable;

/**
 * 封装服务器端返回的结果
 *
 * @author hc
 */
public class ResultBean<T> implements Serializable {
    /** 请求响应状态码 */
    private int code;

    /** 请求结果描述信息 */
    private String msg;

    /** 请求结果数据 */
    private T data;

    public ResultBean() {
    }

    public ResultBean(final int code, final String msg, final T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public ResultBean<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public ResultBean<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ResultBean<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
