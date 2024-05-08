package com.wego.mapper;

import com.wego.entity.domain.City;
import com.wego.entity.query.CityQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/6
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("/spring-mybatis.xml")
class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
        final City city = cityMapper.selectByPrimaryKey(8L);
        System.out.println(city);
    }

    @Test
    void selectList() {
        final List<City> cityList = cityMapper.selectList(CityQuery.builder()
                .provinceId(14L)
                .build());
        cityList.forEach(System.out::println);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void batchDelete() {
        final int res = cityMapper.deleteByIds("414,415,416");
        System.out.println(res);
    }
}