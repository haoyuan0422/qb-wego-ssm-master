package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.base.impl.BaseServiceImpl;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.Goods;
import com.wego.entity.query.GoodsQuery;
import com.wego.mapper.GoodsMapper;
import com.wego.service.GoodsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 商品服务实现类
 * @author: hc
 * @date: 2023/7/6
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageBean<Goods> selectPage(GoodsQuery goodsQuery) {
        if (goodsQuery == null) {
            goodsQuery = new GoodsQuery();
        }
        //设置分页信息
        Page<Goods> page = PageHelper.startPage(goodsQuery.getPageNum(), goodsQuery.getPageSize());
        //查询数据
        goodsMapper.selectList(goodsQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<Goods> pageBean = PageBeanUtil.page2PageBean(page);
        return pageBean;
    }

    @Override
    public List<Goods> selectRecommend(String keywords) {
        final Example example = new Example(Goods.class);
        example.selectProperties("name", "sellingPoint", "info");
        example.createCriteria()
                .orLike("name", "%" + keywords + "%")
                .orLike("sellingPoint", "%" + keywords + "%")
                .orLike("info", "%" + keywords + "%");

        final List<Goods> goodsList = goodsMapper.selectByExampleAndRowBounds(example, new RowBounds(0, 6));

        return goodsList;
    }
}
