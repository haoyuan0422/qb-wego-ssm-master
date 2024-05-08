package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Carousel;
import com.wego.entity.query.CarouselQuery;

import java.util.List;

/**
 * 轮播图服务接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CarouselService extends BaseService<Carousel> {

    /**
     * 查询轮播图列表，给前端首页使用
     * @return
     */
    List<Carousel> selectList();

    /**
     * 分页查找
     * @param carouselQuery
     * @return
     */
    PageBean<Carousel> selectPage(CarouselQuery carouselQuery);
}
