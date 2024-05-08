package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Address;
import com.wego.entity.query.AddressQuery;

/**
 * 地址服务接口
 * @author hc
 */
public interface AddressService extends BaseService<Address> {

    /**
     * 分页查找
     * @param addressQuery
     * @return
     */
    PageBean<Address> selectPage(AddressQuery addressQuery);

}
