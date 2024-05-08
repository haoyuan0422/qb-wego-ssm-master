package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Category;
import com.wego.entity.query.CategoryQuery;

import java.util.List;

/**
 * 类别服务接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CategoryService extends BaseService<Category> {
    /**
     * 查询指定父类别下的子类别
     * @param pid null查询父类别 -1查询所有的子类别
     * @param amount 查询的个数，如果等于-1表示查询所有
     * @param flag 前端调用true 后台调用false
     * @return
     */
    List<Category> selectCategoryByPid(Long pid, Integer amount, boolean flag);

    /**
     * 分页查找
     * @param categoryQuery
     * @return
     */
    PageBean<Category> selectPage(CategoryQuery categoryQuery);
}
