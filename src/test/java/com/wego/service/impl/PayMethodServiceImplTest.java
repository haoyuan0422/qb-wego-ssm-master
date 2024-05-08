package com.wego.service.impl;

import com.wego.entity.domain.PayMethod;
import com.wego.service.PayMethodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

/**
 *
 * @author: hc
 * @date: 2023/7/7
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
public class PayMethodServiceImplTest {

    @Autowired
    private PayMethodService payMethodService;

    @Test
    void insert() {
        PayMethod payMethod = PayMethod.builder()
                .name("haha")
                .priority(23)
                .state(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        final boolean res = payMethodService.insert(payMethod);
        System.out.println(res);
    }

}
