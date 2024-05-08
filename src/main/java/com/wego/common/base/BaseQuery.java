package com.wego.common.base;

import com.wego.constant.WeGoConst;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询基类
 * @author: hc
 * @date: 2023/7/2
 */
@Setter
public class BaseQuery {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 关键字
     */
    @Getter
    private String keywords;

    public Integer getPageNum() {
        if (pageNum == null) {
            return WeGoConst.PAGE_NUM;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return WeGoConst.PAGE_SIZE;
        }
        return pageSize;
    }
}
