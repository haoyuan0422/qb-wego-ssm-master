package com.wego.service.impl;

import com.wego.entity.domain.User;
import com.wego.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/11
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void selectByPrimaryKey() {
        final User user = userService.selectByPrimaryKey(17L);
        System.out.println(user);
    }

    @Test
    void selectByName() {
        final List<User> userList = userService.selectByNickameOrAccount("三");
        userList.forEach(System.out::println);
    }

    @Test
    void selectPage() {

    }
}