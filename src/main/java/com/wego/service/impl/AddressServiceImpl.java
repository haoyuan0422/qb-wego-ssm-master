package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Address;
import com.wego.entity.query.AddressQuery;
import com.wego.mapper.AddressMapper;
import com.wego.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 地址服务实现类
 * @author hc
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public boolean updateByPrimaryKey(Address record) {
        return addressMapper.updateByPrimaryKey(record) >= 0;
    }

    @Override
    public PageBean<Address> selectPage(AddressQuery addressQuery) {
        //设置分页信息
        Page<Address> page = PageHelper.startPage(addressQuery.getPageNum(), addressQuery.getPageSize());
        //查询数据
        addressMapper.selectList(addressQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Address> pageBean = PageBeanUtil.page2PageBean(page);

        return pageBean;
    }

}
