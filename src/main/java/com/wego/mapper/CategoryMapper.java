package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Category;
import com.wego.entity.query.CategoryQuery;

import java.util.List;

/**
 * 类别Mapper接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据条件查询
     * @param categoryQuery
     * @return
     */
    List<Category> selectList(CategoryQuery categoryQuery);

}