package com.wego.service.impl;

import com.wego.entity.domain.Province;
import com.wego.entity.query.ProvinceQuery;
import com.wego.service.ProvinceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author: hc
 * @date: 2023/7/2
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class ProvinceServiceImplTest {

    @Autowired
    private ProvinceService provinceService;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(provinceService);
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
        Province province = provinceService.selectByPrimaryKey(22L);
        System.out.println(province);
    }

    @Test
    void selectPage() {
        ProvinceQuery provinceQuery = new ProvinceQuery();
        provinceQuery.setPageNum(5);
        provinceQuery.setPageSize(3);
        //provinceQuery.setStatus(1);
        provinceService.selectPage(provinceQuery);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}