package com.wego.mapper;

import com.wego.common.base.BaseMapper;
import com.wego.entity.domain.Goods;
import com.wego.entity.query.GoodsQuery;

import java.util.List;

/**
 * 商品Mapper接口
 * @author: hc
 * @date: 2023/7/6
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 根据条件查询
     * @param goodsQuery
     * @return
     */
    List<Goods> selectList(GoodsQuery goodsQuery);
}