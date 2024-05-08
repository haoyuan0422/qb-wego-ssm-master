package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Carousel;
import com.wego.entity.query.CarouselQuery;

import java.util.List;

/**
 * 轮播图Mapper接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CarouselMapper extends BaseMapper<Carousel> {
    /**
     * 根据条件查询
     * @param carouselQuery
     * @return
     */
    List<Carousel> selectList(CarouselQuery carouselQuery);
}