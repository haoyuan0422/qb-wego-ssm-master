package com.wego.converter;

import com.wego.common.bean.EntryBean;
import com.wego.entity.domain.Address;
import com.wego.entity.vo.AddressVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 地址转换器
 * @author hc
 */
@Mapper(componentModel = "spring")
public interface AddressConverter {
    AddressVO address2AddressVO(Address address);

    /**
     * 将map转换成AddressVO
     * @param list 其中依次放置的是country-city-province
     * @param addressVO
     */
    default void list2AddressVO(List<EntryBean<String>> list, AddressVO addressVO) {
        addressVO.setProvinceId((Long) list.get(2).getKey());
        addressVO.setProvinceName(list.get(2).getValue());

        addressVO.setCityId((Long) list.get(1).getKey());
        addressVO.setCityName(list.get(1).getValue());

        addressVO.setCountryId((Long) list.get(0).getKey());
        addressVO.setCountryName(list.get(0).getValue());
    }
}