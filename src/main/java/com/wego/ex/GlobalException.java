package com.wego.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自定义全局异常类
 * @author: hc
 * @date: 2023/7/5
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    /**
     * 异常错误编码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 生成异常时页面的url
     */
    private String url;

    //@Override
    //public String toString() {
    //    //采用拼串的方式生成json
    //    StringBuilder sb = new StringBuilder("{");
    //    sb.append("\"code\":").append(code).append(",");
    //    sb.append("\"msg\":").append("\""+msg+"\"").append(",");
    //    sb.append("\"url\":").append("\""+url+"\"");
    //    sb.append("}");
    //    return sb.toString();
    //}
    //
    //public static void main(String[] args) {
    //    GlobalException ex = new GlobalException(200, "success", "http://localhost:8080/wego/manager/province/deleteById?id=17");
    //    System.out.println(ex);
    //}
}
