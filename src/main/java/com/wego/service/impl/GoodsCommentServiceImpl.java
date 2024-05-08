package com.wego.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.PageBeanUtil;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.query.GoodsCommentQuery;
import com.wego.mapper.GoodsCommentMapper;
import com.wego.service.GoodsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {
    @Autowired
    private GoodsCommentMapper goodsCommentMapper;

    @Override
    public List<GoodsComment> selectList(GoodsCommentQuery goodsCommentQuery) {
        return goodsCommentMapper.selectList(goodsCommentQuery);
    }

    @Override
    public PageBean<GoodsComment> selectPage(GoodsCommentQuery goodsCommentQuery) {
        if (goodsCommentQuery == null) {
            goodsCommentQuery = new GoodsCommentQuery();
        }
        //设置分页信息
        Page<GoodsComment> page = PageHelper.startPage(goodsCommentQuery.getPageNum(), goodsCommentQuery.getPageSize());
        //查询数据
        goodsCommentMapper.selectList(goodsCommentQuery);
        //将MyBatis提供的Page对象转换成我们自己的PageBean对象
        PageBean<GoodsComment> pageBean = PageBeanUtil.page2PageBean(page);

        return pageBean;
    }
}
