package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.GoodsCommentConverter;
import com.wego.entity.domain.Goods;
import com.wego.entity.domain.GoodsComment;
import com.wego.entity.domain.OrderComment;
import com.wego.entity.domain.User;
import com.wego.entity.vo.GoodsCommentVO;
import com.wego.entity.vo.GoodsCommentVOFront;
import com.wego.service.GoodsService;
import com.wego.service.OrderCommentService;
import com.wego.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsCommentTrans {
    @Autowired
    private GoodsCommentConverter goodsCommentConverter;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderCommentService orderCommentService;

    @Getter
    private TransUtil<GoodsComment, GoodsCommentVOFront> instanceFront =  new TransUtil<>() {
        @Override
        public GoodsCommentVOFront tObj2VObj(GoodsComment goodsComment) {
            GoodsCommentVOFront goodsCommentVOFront = goodsCommentConverter.goodsComment2GoodsCommentVOFront(goodsComment);

            OrderComment orderComment = orderCommentService.selectByPrimaryKey(goodsComment.getOrderCommentId());
            User user = userService.selectByPrimaryKey(orderComment.getUserId());
            goodsCommentVOFront.setUserId(user.getId());
            goodsCommentVOFront.setUsername(user.getNickname());

            return goodsCommentVOFront;
        }
    };

    @Getter
    private TransUtil<GoodsComment, GoodsCommentVO> instance = new TransUtil<>() {
        @Override
        public GoodsCommentVO tObj2VObj(GoodsComment goodsComment) {
            GoodsCommentVO goodsCommentVO = goodsCommentConverter.goodsComment2GoodsCommentVO(goodsComment);

            Goods goods = goodsService.selectByPrimaryKey(goodsComment.getGoodsId());
            goodsCommentVO.setGoodsName(goods.getName());

            return goodsCommentVO;
        }
    };
}
