package com.wego.mapper;

import com.wego.entity.domain.Province;
import com.wego.entity.query.ProvinceQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/2
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("/spring-mybatis.xml")
class ProvinceMapperTest {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Test
    void deleteByPrimaryKey() {
        int res = provinceMapper.deleteByPrimaryKey(17L);
        System.out.println(res);
    }

    @Test
    void insert() {
        final Province province = new Province();
        province.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        final int res = provinceMapper.insert(province);
        System.out.println(res);
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
        Province province = provinceMapper.selectByPrimaryKey(24L);
        System.out.println(province);
    }

    @Test
    void selectList() {
        ProvinceQuery provinceQuery = new ProvinceQuery();
        //provinceQuery.setId(8L);
        //provinceQuery.setName("四川");
        provinceQuery.setArea("华中");
        //provinceQuery.setState(1);
        List<Province> list = provinceMapper.selectList(provinceQuery);
        list.forEach(System.out::println);
    }

    @Test
    void selectList2() {
        List<Province> list = provinceMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void batchDelete() {
        long res = provinceMapper.batchDelete("149,150".split(","));
        System.out.println(res);
    }
}