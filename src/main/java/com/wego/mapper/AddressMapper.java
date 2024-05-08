package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Address;
import com.wego.entity.query.AddressQuery;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 地址Mapper接口
 * @author hc
 *
 * @CacheNamespace //开启tk.mybatis的二级缓存
 */
@CacheNamespace
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 根据条件查询
     *
     * @param addressQuery
     * @return
     */
    List<Address> selectList(AddressQuery addressQuery);
}