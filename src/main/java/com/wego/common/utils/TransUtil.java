package com.wego.common.utils;

import com.wego.common.bean.PageBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>
 * @param <V>
 */
public abstract class TransUtil<T, V> {
    /**
     * PageBean转换
     * @param tPageBean
     * @return
     */
    public PageBean<V> tPageBean2VPageBean(PageBean<T> tPageBean) {
        PageBean<V> vPageBean = PageBeanUtil.pageBean2PageBean(tPageBean);

        final List<T> tList = tPageBean.getResult();
        List<V> vList = tList2VList(tList);
        vPageBean.setResult(vList);

        return vPageBean;
    }

    /**
     * List转换
     * @param tList
     * @return
     */
    public List<V> tList2VList(List<T> tList) {
        List<V> vList = new ArrayList<>();
        for (T tObj : tList) {
            final V vObj = tObj2VObj(tObj);
            vList.add(vObj);
        }
        return vList;
    }

    /**
     * 对象转换
     * @param Obj
     * @return
     */
    public abstract V tObj2VObj(T Obj);
}
