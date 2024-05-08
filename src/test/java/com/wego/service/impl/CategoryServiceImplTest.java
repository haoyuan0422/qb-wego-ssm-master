package com.wego.service.impl;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Category;
import com.wego.entity.query.CategoryQuery;
import com.wego.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/10
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void selectTopCategory() {
        final List<Category> categoryList = categoryService.selectCategoryByPid(null, -1, false);
        categoryList.forEach(System.out::println);
    }

    @Test
    void selectCategoryByPid() {
        final List<Category> categoryList = categoryService.selectCategoryByPid(3L, -1, false);
        categoryList.forEach(System.out::println);
    }

    @Test
    void selectCategoryByPid2() {
        final List<Category> categoryList = categoryService.selectCategoryByPid(1L, 8, true);
        categoryList.forEach(System.out::println);
    }

    @Test
    void selectPage() {
        final PageBean<Category> pageBean = categoryService.selectPage(CategoryQuery.builder()
                .build());
        System.out.println(pageBean);
    }
}