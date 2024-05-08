package com.wego.service.impl;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Carousel;
import com.wego.entity.query.CarouselQuery;
import com.wego.service.CarouselService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author: hc
 * @date: 2023/7/11
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/spring-context.xml", "/spring-mybatis.xml" })
class CarouselServiceImplTest {

    @Autowired
    private CarouselService carouselService;

    @Test
    void selectPage() {
        final PageBean<Carousel> pageBean = carouselService.selectPage(CarouselQuery.builder()

                .build());
        System.out.println(pageBean);
    }

    @Test
    void selectByPrimaryKey() {
        final Carousel carousel = carouselService.selectByPrimaryKey(12L);
        System.out.println(carousel);
    }
}