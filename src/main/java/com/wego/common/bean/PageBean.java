package com.wego.common.bean;

import com.wego.common.utils.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 用户单击页码时，服务器端返回的数据
 * @param <T>
 * @author hc
 */
@Getter
@Setter
@NoArgsConstructor
public class PageBean<T> {
    /**
     * 每页显示的条数
     */
    private Integer pageSize;
    /**
     * 当前的页码
     */
    private Integer pageNum;
    /**
     * 一共有多少条记录
     */
    private Long total;
    /**
     * 一共有多少页
     */
    private Integer pages;
    /**
     * 每一页所显示的数据
     */
    private List<T> result;

    /**
     * 分页请求路径
     */
    private String url;

    @Override
    public String toString() {
        return JsonUtil.obj2String(this);
    }
}
