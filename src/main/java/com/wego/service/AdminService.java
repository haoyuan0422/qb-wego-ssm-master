package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Admin;
import com.wego.entity.query.AdminQuery;

/**
 * 管理员服务接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface AdminService extends BaseService<Admin> {
    /**
     * 分页查找
     * @param adminQuery
     * @return
     */
    PageBean<Admin> selectPage(AdminQuery adminQuery);

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    Admin login(String account, String password);

    /**
     * 判断指定账户的管理员是否存在
     * @param account
     * @return
     */
    boolean accountExist(String account);
}
