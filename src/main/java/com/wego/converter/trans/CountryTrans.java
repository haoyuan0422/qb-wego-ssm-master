package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.CountryConverter;
import com.wego.entity.domain.City;
import com.wego.entity.domain.Country;
import com.wego.entity.vo.CountryVO;
import com.wego.service.CityService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryTrans {
    @Autowired
    private CityService cityService;

    @Autowired
    private CountryConverter countryConverter;

    @Getter
    private TransUtil<Country, CountryVO> instance = new TransUtil<>() {
        @Override
        public CountryVO tObj2VObj(Country country) {
            CountryVO countryVO = countryConverter.country2CountryVO(country);

            final City city = cityService.selectByPrimaryKey(country.getCityId());
            countryVO.setCityName(city.getName());

            return countryVO;
        }
    };
}
