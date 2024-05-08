package com.wego.converter;

import com.wego.common.bean.PageBean;
import com.wego.entity.domain.Goods;
import com.wego.entity.vo.CartItemVO;
import com.wego.entity.vo.GoodsDetialsVOFront;
import com.wego.entity.vo.GoodsVO;
import com.wego.entity.vo.GoodsVOFront;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 商品转换器
 * @author: hc
 * @date: 2023/7/6
 */
@Mapper(componentModel = "spring")
public interface GoodsConverter {

    /**
     * 前端商品详情页面用
     * @param goods
     * @return
     */
    GoodsDetialsVOFront goods2GoodsDetailsVOFront(Goods goods);

    /**
     * 后台列表展示用
     * @param goodsPageBean
     * @return
     */
    PageBean<GoodsVO> goodsPageBean2GoodsVOPageBean(PageBean<Goods> goodsPageBean);

    /**
     * 前端页面展示用
     * @param goodsPageBean
     * @return
     */
    PageBean<GoodsVOFront> goodsPageBean2GoodsVOFrontPageBean(PageBean<Goods> goodsPageBean);

    @Mappings({
            @Mapping(source = "id", target = "goodsId"),
            @Mapping(source = "name", target = "goodsName"),
            @Mapping(source = "pic", target = "goodsPic"),
            @Mapping(source = "price1", target = "goodsPrice1"),
            @Mapping(source = "price2", target = "goodsPrice2"),
            @Mapping(source = "unit", target = "goodsUnit"),
            @Mapping(source = "sellingPoint", target = "goodsSellingPoint")
    })
    CartItemVO goods2CartItemVO(Goods goods);
}
