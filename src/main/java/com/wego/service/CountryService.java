package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Country;
import com.wego.entity.query.CountryQuery;

import java.util.List;

/**
 *
 * 县区服务接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CountryService extends BaseService<Country> {

    /**
     * 分页查找
     * @param countryQuery
     * @return
     */
    PageBean<Country> selectPage(CountryQuery countryQuery);

    /**
     * 查询指定县区所属的省市县，包含各自的id
     * @param countryId 县区主键
     * @return
     */
    List<EntryBean<String>> selectProvinceCityCountryByCountryId(Long countryId);
}
