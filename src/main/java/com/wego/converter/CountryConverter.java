package com.wego.converter;

import com.wego.common.base.BaseConverter;
import com.wego.common.bean.EntryBean;
import com.wego.entity.domain.Country;
import com.wego.entity.vo.CountryVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 县区转换器
 * @author: hc
 * @date: 2023/7/9
 */
@Mapper(componentModel = "spring")
public interface CountryConverter extends BaseConverter<Country> {
    CountryVO country2CountryVO(Country country);

    List<EntryBean<String>> countryList2EntryList(List<Country> countryList);
}
