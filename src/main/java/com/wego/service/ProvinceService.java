package com.wego.service;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Province;
import com.wego.entity.query.ProvinceQuery;

import java.util.List;

/**
 * 省份服务接口
 * @author: hc
 * @date: 2023/7/2
 */
public interface ProvinceService {
    boolean deleteByPrimaryKey(Long id);

    boolean insert(Province record);

    boolean insertSelective(Province record);

    Province selectByPrimaryKey(Long id);

    boolean updateByPrimaryKeySelective(Province record);

    boolean updateByPrimaryKey(Province record);

    /**
     * 分页查找
     * @param provinceQuery
     * @return
     */
    PageBean<Province> selectPage(ProvinceQuery provinceQuery);

    List<Province> selectAll();

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean batchDelete(String ids);

    boolean batchUpdateSelective(List<Province> list);
}

