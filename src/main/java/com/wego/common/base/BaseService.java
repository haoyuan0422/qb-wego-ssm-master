package com.wego.common.base;

import java.util.List;

/**
 *
 * @author: hc
 * @date: 2023/7/7
 */
public interface BaseService<T> {
    /**
     * 根据条件查询
     * @param obj
     * @return
     */
    List<T> selectList(T obj);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    boolean deleteByPrimaryKey(Object id);

    /**
     * 插入数据
     * @param record
     * @return
     */
    boolean insert(T record);

    /**
     * 选择性插入数据
     * @param record
     * @return
     */
    boolean insertSelective(T record);

    /**
     * 根据主键查找
     * @param id
     * @return
     */
    T selectByPrimaryKey(Object id);

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    boolean updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(T record);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean batchDelete(String ids);

    /**
     * 根据id批量修改状态
     * @param list
     * @return
     */
    boolean batchUpdateSelective(List<T> list);
}
