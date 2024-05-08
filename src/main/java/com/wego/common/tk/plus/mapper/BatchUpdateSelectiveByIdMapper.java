package com.wego.common.tk.plus.mapper;

import com.wego.common.tk.plus.provider.BatchUpdateSelectiveByIdProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/8
 */
@RegisterMapper
public interface BatchUpdateSelectiveByIdMapper<T> {
    /**
     * 选择性批量更新
     * @param list
     * @return
     */
    @UpdateProvider(type = BatchUpdateSelectiveByIdProvider.class, method = "dynamicSQL")
    int batchUpdateSelective(List<T> list);
}
