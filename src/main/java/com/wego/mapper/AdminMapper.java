package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Admin;
import com.wego.entity.query.AdminQuery;

import java.util.List;

/**
 * 管理员Mapper接口
 * @author: hc
 * @date: 2023/7/7
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 根据条件查询
     * @param adminQuery
     * @return
     */
    List<Admin> selectList(AdminQuery adminQuery);
}