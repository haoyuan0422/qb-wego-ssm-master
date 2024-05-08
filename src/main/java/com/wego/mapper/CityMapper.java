package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.City;
import com.wego.entity.query.CityQuery;

import java.util.List;

/**
 * 城市Mapper接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface CityMapper extends BaseMapper<City> {

    /**
     * 根据条件查询
     * @param cityQuery
     * @return
     */
    List<City> selectList(CityQuery cityQuery);

}