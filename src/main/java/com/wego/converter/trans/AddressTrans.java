package com.wego.converter.trans;

import com.wego.common.bean.EntryBean;
import com.wego.common.utils.TransUtil;
import com.wego.converter.AddressConverter;
import com.wego.entity.domain.Address;
import com.wego.entity.vo.AddressVO;
import com.wego.service.CountryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressTrans {
    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private CountryService countryService;

    @Getter
    private TransUtil<Address, AddressVO> instance = new TransUtil<>() {
        @Override
        public AddressVO tObj2VObj(Address address) {
            AddressVO addressVO = addressConverter.address2AddressVO(address);

            final List<EntryBean<String>> entryBeanList = countryService.selectProvinceCityCountryByCountryId(address.getCountryId());
            addressConverter.list2AddressVO(entryBeanList, addressVO);

            return addressVO;
        }
    };
}
