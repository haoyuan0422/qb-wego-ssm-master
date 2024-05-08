package com.wego.converter;

import com.wego.common.base.BaseConverter;
import com.wego.common.bean.EntryBean;
import com.wego.entity.domain.City;
import com.wego.entity.vo.CityVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 城市转换器
 * @author: hc
 * @date: 2023/7/9
 */
@Mapper(componentModel = "spring")
public interface CityConverter extends BaseConverter<City> {

    CityVO city2CityVO(City city);

    List<EntryBean<String>> cityList2EntryList(List<City> cityList);
}
