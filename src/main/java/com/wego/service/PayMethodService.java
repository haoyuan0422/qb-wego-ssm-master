package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.PayMethod;
import com.wego.entity.query.PayMethodQuery;

/**
 * 支付方式服务接口
 * @author: hc
 * @date: 2023/7/5
 */
public interface PayMethodService extends BaseService<PayMethod> {
    /**
     * 分页查找
     * @param payMethodQuery
     * @return
     */
    PageBean<PayMethod> selectPage(PayMethodQuery payMethodQuery);
}
