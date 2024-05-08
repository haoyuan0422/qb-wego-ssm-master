package com.wego.common.utils;

import com.github.pagehelper.Page;
import com.wego.common.bean.PageBean;

/**
 * PageBean工具类
 *
 * @author: hc
 * @date: 2023/5/4
 */
public class PageBeanUtil {

    /**
     * 将MyBatis的Page对象转换成我们自定义的PageBean对象
     *
     * @param page
     */
    public static <T> PageBean<T> page2PageBean(Page<T> page) {
        if (page == null) {
            return null;
        }
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setPageSize(page.getPageSize());
        pageBean.setPageNum(page.getPageNum());
        pageBean.setTotal(page.getTotal());
        pageBean.setPages(page.getPages());
        pageBean.setResult(page.getResult());
        return pageBean;
    }

    /**
     * 将PageBean<T>对象转换成PageBean<V>对象
     *
     * @param page
     */
    public static <T, V> PageBean<V> pageBean2PageBean(PageBean<T> page) {
        if (page == null) {
            return null;
        }
        PageBean<V> pageBean = new PageBean<>();
        pageBean.setPageSize(page.getPageSize());
        pageBean.setPageNum(page.getPageNum());
        pageBean.setTotal(page.getTotal());
        pageBean.setPages(page.getPages());
        return pageBean;
    }

}
