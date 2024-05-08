package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.City;
import com.wego.entity.query.CityQuery;

/**
 * 城市服务接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CityService extends BaseService<City> {

    /**
     * 分页查找
     * @param cityQuery
     * @return
     */
    PageBean<City> selectPage(CityQuery cityQuery);

}
