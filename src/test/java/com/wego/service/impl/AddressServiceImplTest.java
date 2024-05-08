package com.wego.service.impl;

import com.wego.entity.domain.Address;
import com.wego.service.AddressService;
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
class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    /**
     * 用来测试二级缓存
     */
    @Test
    void selectByPrimaryKey() {
        Address address = addressService.selectByPrimaryKey(5L);
        System.out.println(address);

        address = addressService.selectByPrimaryKey(5L);
        System.out.println(address);
    }

    @Test
    void selectPage() {
        final List<Address> addressList = addressService.selectList(Address.builder()
                .userId(null)
                .build());
        addressList.forEach(System.out::println);
    }
}