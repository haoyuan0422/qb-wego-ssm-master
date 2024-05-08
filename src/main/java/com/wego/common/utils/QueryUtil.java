package com.wego.common.utils;

import com.wego.common.base.BaseQuery;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 查询工具类
 * @author: hc
 * @date: 2023/7/3
 */
public class QueryUtil {
    /**
     * 根据查询对象构造查询条件
     * @param baseQuery
     * @return
     * @throws Exception
     */
    public static String genQueryCondition(BaseQuery baseQuery) {
        //内省
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(baseQuery.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        String params = "";

        for (PropertyDescriptor pd : pds) {
            Method readMethod = pd.getReadMethod();
            final String readMethodName = readMethod.getName();
            if (!"getClass".equals(readMethodName) && !"getPageNum".equals(readMethodName)) {
                Object value = null;
                try {
                    value = readMethod.invoke(baseQuery, null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    params += "&" + pd.getName() + "=" + value;
                }
            }
        }
        return params.substring(1);
    }

    public static void main(String[] args) throws Exception {
        //ProvinceQuery provinceQuery = new ProvinceQuery();
        //provinceQuery.setId(1234L);
        //provinceQuery.setName("省");
        //
        //String params = QueryUtil.genQueryCondition(provinceQuery);
        //System.out.println(params);


        //GoodsQuery goodsQuery = GoodsQuery.builder()
        //        .id(234L)
        //        .name("aa")
        //        .sellingPoint("fdsa")
        //        .categoryId(3L)
        //        .info("fds")
        //        .status(3)
        //        .build();
        //String res = QueryUtil.genQueryCondition(goodsQuery);
        //System.out.println(res);
    }
}
