package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.User;
import com.wego.entity.query.UserQuery;

import java.util.List;

/**
 * 用户Mapper接口
 *  @author hc
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据条件查询
     * @param userQuery
     * @return
     */
    List<User> selectList(UserQuery userQuery);

}