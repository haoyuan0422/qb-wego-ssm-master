package com.wego.converter;

import com.wego.common.bean.EntryBean;
import com.wego.entity.domain.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 省份转换器
 * @author: hc
 * @date: 2023/7/9
 */
@Mapper(componentModel = "spring")
public interface ProvinceConverter {
    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "name", target = "value")
    })
    EntryBean<String> province2Entry(Province province);

    List<EntryBean<String>> provinceList2EntryList(List<Province> provinceList);
}
