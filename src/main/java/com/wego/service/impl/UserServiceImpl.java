package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.User;
import com.wego.entity.query.UserQuery;
import com.wego.mapper.UserMapper;
import com.wego.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户服务实现类
 *  @author hc
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectByNickameOrAccount(String name) {
        final Example example = new Example(User.class);
        example.createCriteria().andLike("nickname", "%" + name + "%").orLike("account", "%" + name + "%");
        final List<User> userList = userMapper.selectByExample(example);
        return userList;
    }

    @Override
    public PageBean<User> selectPage(UserQuery userQuery) {
        if (userQuery == null) {
            userQuery = new UserQuery();
        }
        //设置分页信息
        Page<User> page = PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        //查询数据
        userMapper.selectList(userQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<User> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

    @Override
    public boolean accountExist(String account) {
        List<User> adminList = userMapper.select(User.builder()
                .account(account)
                .build());
        return adminList.size() > 0;
    }

    @Override
    public User login(String account, String password) {
        List<User> adminList = userMapper.select(User.builder()
                .account(account)
                .password(password)
                .state(1)
                .build());
        if (adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }
}
