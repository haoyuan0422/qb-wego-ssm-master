package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.OrderItemConverter;
import com.wego.entity.domain.Goods;
import com.wego.entity.domain.OrderItem;
import com.wego.entity.vo.OrderItemVO;
import com.wego.service.GoodsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemTrans {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderItemConverter orderItemConverter;

    @Getter
    private TransUtil<OrderItem, OrderItemVO> instance = new TransUtil<>() {
        @Override
        public OrderItemVO tObj2VObj(OrderItem orderItem) {
            OrderItemVO orderItemVO = orderItemConverter.orderItem2OrderItemVO(orderItem);

            final Goods goods = goodsService.selectByPrimaryKey(orderItem.getGoodsId());
            orderItemVO.setGoodsName(goods.getName());

            orderItemVO.setGoodsPic(goods.getPic());
            orderItemVO.setGoodsSellingPoint(goods.getSellingPoint());
            //设置购买价格
            orderItemVO.setGoodsPrice(goods.getMemberPrice());
            orderItemVO.setGoodsUnit(goods.getUnit());

            return orderItemVO;
        }
    };

}
