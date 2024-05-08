package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Admin;
import com.wego.entity.query.AdminQuery;
import com.wego.mapper.AdminMapper;
import com.wego.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员服务实现类
 * @author: hc
 * @date: 2023/7/5
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageBean<Admin> selectPage(AdminQuery adminQuery) {
        //设置分页信息
        Page<Admin> page = PageHelper.startPage(adminQuery.getPageNum(), adminQuery.getPageSize());
        //查询数据
        adminMapper.selectList(adminQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Admin> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

    @Override
    public Admin login(String account, String password) {
        List<Admin> adminList = adminMapper.select(Admin.builder()
                .account(account)
                .password(password)
                .state(1)
                .build());
        if (adminList.size() > 0) {
            Admin admin = adminList.get(0);
            return admin;
        }
        return null;
    }

    @Override
    public boolean accountExist(String account) {
        List<Admin> adminList = adminMapper.select(Admin.builder()
                .account(account)
                .build());
        return adminList.size() > 0;
    }
}
