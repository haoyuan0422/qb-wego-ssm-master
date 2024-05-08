package com.wego.mapper;

import com.wego.entity.domain.Province;
import com.wego.entity.query.ProvinceQuery;

import java.util.List;

/**
 * 省份Mapper接口
 * @author: hc
 * @date: 2023/7/2
 */
public interface ProvinceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    /**
     * 根据条件查询
     * @param provinceQuery
     * @return
     */
    List<Province> selectList(ProvinceQuery provinceQuery);

    /**
     * 批量修改状态
     * @param provinceList
     * @return 返回数据库中受影响的记录的行数
     */
    long batchUpdateSelective(List<Province> provinceList);

    /**
     * 批量删除
     * @param split
     * @return
     */
    long batchDelete(String[] split);
}