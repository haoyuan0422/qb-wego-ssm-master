package com.wego.converter;

import com.wego.common.base.BaseConverter;
import com.wego.common.bean.EntryBean;
import com.wego.entity.domain.Category;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 类别转换器
 * @author: hc
 * @date: 2023/7/6
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter extends BaseConverter<Category> {
    List<EntryBean<String>> categoryList2EntryList(List<Category> categoryList);
}
