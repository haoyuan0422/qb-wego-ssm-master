package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.City;
import com.wego.entity.query.CityQuery;
import com.wego.mapper.CityMapper;
import com.wego.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市服务实现类
 * @author: hc
 * @date: 2023/7/5
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public PageBean<City> selectPage(CityQuery cityQuery) {
        if (cityQuery == null) {
            cityQuery = new CityQuery();
        }
        //设置分页信息
        Page<City> page = PageHelper.startPage(cityQuery.getPageNum(), cityQuery.getPageSize());
        //查询数据
        cityMapper.selectList(cityQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<City> pageBean = PageBeanUtil.page2PageBean(page);

        return pageBean;
    }

}
