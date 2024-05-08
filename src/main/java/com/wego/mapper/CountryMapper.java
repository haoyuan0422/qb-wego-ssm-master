package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Country;
import com.wego.entity.query.CountryQuery;

import java.util.List;

/**
 * 县区Mapper接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CountryMapper extends BaseMapper<Country> {

    List<Country> selectList(CountryQuery countryQuery);
}