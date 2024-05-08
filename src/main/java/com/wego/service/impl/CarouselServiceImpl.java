package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Carousel;
import com.wego.entity.query.CarouselQuery;
import com.wego.mapper.CarouselMapper;
import com.wego.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 轮播图服务实现类
 * @author: hc
 * @date: 2023/7/5
 */
@Service
public class CarouselServiceImpl extends BaseServiceImpl<Carousel> implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> selectList() {
        PageHelper.startPage(1, 5);
        final Example example = new Example(Carousel.class);
        example.setOrderByClause("priority DESC,create_time DESC");
        example.createCriteria()
                .andEqualTo("state", 1);
        return carouselMapper.selectByExample(example);
    }

    @Override
    public PageBean<Carousel> selectPage(CarouselQuery carouselQuery) {
        //设置分页信息
        Page<Carousel> page = PageHelper.startPage(carouselQuery.getPageNum(), carouselQuery.getPageSize());
        //查询数据
        carouselMapper.selectList(carouselQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Carousel> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

}
