package com.wego.constant;

import java.util.Map;

/**
 * 全局常量
 * @author: hc
 * @date: 2023/7/4
 */
public interface WeGoConst {
    /**
     * 页码
     */
    Integer PAGE_NUM = 1;
    /**
     * 页面大小
     */
    Integer PAGE_SIZE = 56;

    /**
     * 图片服务器网址
     */
    String RESOURCES_SERVER = "http://127.0.0.1:9005/wego_resources_server";

    /**
     * 订单状态
     */
    Map<Integer, String> ORDER_STATE = Map.of(1, "已下单但未付款", 2, "已付款", 3, "已发货", 4, "已签收", 5, "已评价", 6, "已追加评价");

    /**
     * 校验码
     */
    String VERIFY_CODE = "verifyCode";

    /**
     * Session中管理员的key
     */
    String SESSION_ADMIN = "admin";

    /**
     * Session中管理员的key
     */
    String SESSION_USER = "user";

    String CART = "cart";

}
