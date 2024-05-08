package com.wego.service;

import com.wego.common.base.BaseService;
import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Goods;
import com.wego.entity.query.GoodsQuery;

import java.util.List;

/**
 * 商品服务接口
 * @author: hc
 * @date: 2023/7/6
 */
public interface GoodsService extends BaseService<Goods> {

    /**
     * 分页查找
     * @param goodsQuery
     * @return
     */
    PageBean<Goods> selectPage(GoodsQuery goodsQuery);

    /**
     * 前端页面搜索自动补全提示用
     * @param keywords
     * @return
     */
    List<Goods> selectRecommend(String keywords);
}
