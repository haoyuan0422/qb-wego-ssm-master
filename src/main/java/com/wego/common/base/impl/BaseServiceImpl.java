package com.wego.common.base.impl;

import com.wego.common.base.BaseMapper;
import com.wego.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/7
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public List<T> selectList(T obj) {
        return baseMapper.select(obj);
    }

    @Override
    public boolean deleteByPrimaryKey(Object id) {
        return baseMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean insert(T record) {
        return baseMapper.insert(record) == 1;
    }

    @Override
    public boolean insertSelective(T record) {
        return baseMapper.insertSelective(record) == 1;
    }

    @Override
    public T selectByPrimaryKey(Object id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record) == 1;
    }

    @Override
    public boolean updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record) == 1;
    }

    @Override
    public boolean batchDelete(String ids) {
        return baseMapper.deleteByIds(ids) >= 0;
    }

    @Override
    public boolean batchUpdateSelective(List<T> list) {
        return baseMapper.batchUpdateSelective(list) >= 0;
    }
}
