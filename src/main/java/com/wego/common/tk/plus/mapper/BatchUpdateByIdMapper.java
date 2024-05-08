package com.wego.common.tk.plus.mapper;

import com.wego.common.tk.plus.provider.BatchUpdateByIdProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/8
 */
@RegisterMapper
public interface BatchUpdateByIdMapper<T> {
    /**
     * 批量更新
     * @param list
     * @return
     */
    @UpdateProvider(type = BatchUpdateByIdProvider.class, method = "dynamicSQL")
    int batchUpdate(List<T> list);
}
