package com.wego.service.impl;

import com.wego.common.bean.EntryBean;
import com.wego.entity.domain.Country;
import com.wego.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/12
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class CountryServiceImplTest {

    @Autowired
    private CountryService countryService;

    @Test
    void selectProvinceCityCountryByCountryId() {
        final List<EntryBean<String>> list = countryService.selectProvinceCityCountryByCountryId(234L);
        list.forEach(System.out::println);
    }

    @Test
    void selectByCityId() {
        final List<Country> countryList = countryService.selectList(Country.builder()
                .cityId(23L)
                .build());
        countryList.forEach(System.out::println);
    }
}