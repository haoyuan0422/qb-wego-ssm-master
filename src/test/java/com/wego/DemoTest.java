package com.wego;

import com.wego.common.bean.EntryBean;
import com.wego.converter.ProvinceConverter;
import com.wego.entity.domain.Province;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/3
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
public class DemoTest {

    @Autowired
    private ProvinceConverter provinceConverter;

    @Test
    public void fun() {
        List<Province> list = new ArrayList<>();
        list.add(Province.builder().id(1111L).name("aa").build());
        list.add(Province.builder().id(2222L).name("bb").build());
        final List<EntryBean<String>> res = provinceConverter.provinceList2EntryList(list);
        res.forEach(System.out::println);
    }
}
