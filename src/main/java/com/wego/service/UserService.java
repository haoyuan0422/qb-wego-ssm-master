package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.User;
import com.wego.entity.query.UserQuery;

import java.util.List;

/**
 * 用户服务接口
 *  @author hc
 */
public interface UserService extends BaseService<User> {

    /**
     * 查询指定昵称or账户的用户的信息
     * @param name
     * @return
     */
    List<User> selectByNickameOrAccount(String name);

    /**
     * 分页查找
     *
     * @param userQuery
     * @return
     */
    PageBean<User> selectPage(UserQuery userQuery);

    /**
     * 判断指定账户的用户是否存在
     * @param account
     * @return
     */
    boolean accountExist(String account);

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public User login(String account, String password);
}

