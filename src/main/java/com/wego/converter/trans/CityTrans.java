package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.CityConverter;
import com.wego.entity.domain.City;
import com.wego.entity.domain.Province;
import com.wego.entity.vo.CityVO;
import com.wego.service.ProvinceService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityTrans {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityConverter cityConverter;

    @Getter
    private TransUtil<City, CityVO> instance = new TransUtil<>() {
        @Override
        public CityVO tObj2VObj(City city) {
            CityVO cityVO = cityConverter.city2CityVO(city);

            final Province province = provinceService.selectByPrimaryKey(city.getProvinceId());
            cityVO.setProvinceName(province.getName());

            return cityVO;
        }
    };
}
