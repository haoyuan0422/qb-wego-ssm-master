package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.PayMethod;
import com.wego.entity.query.PayMethodQuery;

import java.util.List;

/**
 * 支付方式Mapper接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface PayMethodMapper extends BaseMapper<PayMethod> {

    List<PayMethod> selectList(PayMethodQuery payMethodQuery);
}