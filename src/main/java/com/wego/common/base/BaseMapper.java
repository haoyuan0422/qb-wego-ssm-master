package com.wego.common.base;

import com.wego.common.tk.plus.mapper.BatchUpdateSelectiveByIdMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

/**
 *
 * @author: hc
 * @date: 2023/7/8
 */
@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T>, DeleteByIdsMapper<T>, BatchUpdateSelectiveByIdMapper<T> {
}
