package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Cart;
import com.wego.entity.query.CartQuery;
import com.wego.mapper.CartMapper;
import com.wego.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购物车服务实现类
 *  @author hc
 */
@Service
public class CartServiceImpl extends BaseServiceImpl<Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public PageBean<Cart> selectPage(CartQuery cartQuery) {
        //设置分页信息
        Page<Cart> page = PageHelper.startPage(cartQuery.getPageNum(), cartQuery.getPageSize());
        //查询数据
        cartMapper.selectList(cartQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Cart> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

}
