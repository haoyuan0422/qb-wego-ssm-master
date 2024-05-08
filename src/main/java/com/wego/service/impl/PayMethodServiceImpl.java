package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.PayMethod;
import com.wego.entity.query.PayMethodQuery;
import com.wego.mapper.PayMethodMapper;
import com.wego.service.PayMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付方式服务实现类
 * @author: hc
 * @date: 2023/7/5
 */
@Service
public class PayMethodServiceImpl extends BaseServiceImpl<PayMethod> implements PayMethodService {

    @Autowired
    private PayMethodMapper payMethodMapper;

    @Override
    public PageBean<PayMethod> selectPage(PayMethodQuery payMethodQuery) {
        if (payMethodQuery == null) {
            payMethodQuery = new PayMethodQuery();
        }
        //设置分页信息
        Page<PayMethod> page = PageHelper.startPage(payMethodQuery.getPageNum(), payMethodQuery.getPageSize());
        //查询数据
        payMethodMapper.selectList(payMethodQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<PayMethod> pageBean = PageBeanUtil.page2PageBean(page);
        System.out.println(pageBean);
        return pageBean;
    }

}
