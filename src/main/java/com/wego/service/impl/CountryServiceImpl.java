package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.City;
import com.wego.entity.domain.Country;
import com.wego.entity.domain.Province;
import com.wego.entity.query.CountryQuery;
import com.wego.mapper.CityMapper;
import com.wego.mapper.CountryMapper;
import com.wego.mapper.ProvinceMapper;
import com.wego.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 县区服务实现类
 * @author: hc
 * @date: 2023/7/5
 */
@Service
public class CountryServiceImpl extends BaseServiceImpl<Country> implements CountryService {
    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public PageBean<Country> selectPage(CountryQuery countryQuery) {
        if (countryQuery == null) {
            countryQuery = new CountryQuery();
        }
        //设置分页信息
        Page<Country> page = PageHelper.startPage(countryQuery.getPageNum(), countryQuery.getPageSize());
        //查询数据
        countryMapper.selectList(countryQuery);

        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Country> pageBean = PageBeanUtil.page2PageBean(page);

        return pageBean;
    }

    @Override
    public List<EntryBean<String>> selectProvinceCityCountryByCountryId(Long countryId) {
        List<EntryBean<String>> list = new ArrayList<>();

        final Country country = countryMapper.selectByPrimaryKey(countryId);
        list.add(new EntryBean(countryId, country.getName()));

        final City city = cityMapper.selectByPrimaryKey(country.getCityId());
        list.add(new EntryBean(city.getId(), city.getName()));

        final Province province = provinceMapper.selectByPrimaryKey(city.getProvinceId());
        list.add(new EntryBean(province.getId(), province.getName()));

        return list;
    }

}
