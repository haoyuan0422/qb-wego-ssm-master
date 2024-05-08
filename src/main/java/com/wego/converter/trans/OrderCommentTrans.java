package com.wego.converter.trans;

import com.wego.common.utils.TransUtil;
import com.wego.converter.OrderCommentConverter;
import com.wego.entity.domain.OrderComment;
import com.wego.entity.domain.User;
import com.wego.entity.vo.OrderCommentVO;
import com.wego.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCommentTrans {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderCommentConverter orderCommentConverter;

    @Getter
    private TransUtil<OrderComment, OrderCommentVO> instance = new TransUtil<>() {
        @Override
        public OrderCommentVO tObj2VObj(OrderComment orderComment) {
            OrderCommentVO orderCommentVO = orderCommentConverter.comment2CommentVO(orderComment);

            final User user = userService.selectByPrimaryKey(orderComment.getUserId());
            orderCommentVO.setUserName(user.getNickname());

            return orderCommentVO;
        }
    };
}
