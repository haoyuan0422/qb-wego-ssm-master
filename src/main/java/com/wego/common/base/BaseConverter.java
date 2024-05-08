package com.wego.common.base;

import com.wego.common.bean.EntryBean;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 类型转换父接口<br>
 *
 * @author: hc
 * @date 2023/7/14
 */
public interface BaseConverter<T> {
    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "name", target = "value")
    })
    EntryBean<String> obj2Entry(T obj);
}
