package com.wego.service.impl;

import com.wego.entity.domain.Admin;
import com.wego.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author: hc
 * @date: 2023/7/7
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @Test
    void selectByPrimaryKey() {
        System.out.println(adminService);
        System.out.println(adminService.selectByPrimaryKey(1009L));
    }

    @Test
    void login() {
        Admin admin = adminService.login("zhangsan2", "zhangsan");
        System.out.println(admin);
    }
}
