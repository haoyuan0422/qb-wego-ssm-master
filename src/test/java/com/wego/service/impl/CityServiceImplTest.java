package com.wego.service.impl;

import com.wego.entity.domain.City;
import com.wego.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/7
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
public class CityServiceImplTest {

    @Autowired
    private CityService cityService;

    @Test
    void selectByPrimaryKey() {
        System.out.println(cityService);
        System.out.println(cityService.selectByPrimaryKey(333L));
    }

    @Test
    void batchDelete() {
        final boolean res = cityService.batchDelete("417,418,419");
        System.out.println(res);
    }

    @Test
    void selectByProvinceId() {
        final List<City> cityList = cityService.selectList(City.builder()
                .provinceId(13L)
                .build());
        cityList.forEach(System.out::println);
    }

}
