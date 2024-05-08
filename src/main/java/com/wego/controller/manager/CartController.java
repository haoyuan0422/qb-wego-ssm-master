package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.CartItemConverter;
import com.wego.converter.trans.CartTrans;
import com.wego.entity.domain.Cart;
import com.wego.entity.domain.CartItem;
import com.wego.entity.domain.Goods;
import com.wego.entity.domain.User;
import com.wego.entity.query.CartItemQuery;
import com.wego.entity.query.CartQuery;
import com.wego.entity.vo.CartItemVO;
import com.wego.entity.vo.CartVO;
import com.wego.service.CartItemService;
import com.wego.service.CartService;
import com.wego.service.GoodsService;
import com.wego.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 购物车控制器
 *  @author hc
 */
@Slf4j
@Controller
@RequestMapping("/manager/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CartItemConverter cartItemConverter;

    @Autowired
    private CartTrans cartTrans;

    /**
     * 省份列表
     * @param cartQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(CartQuery cartQuery, Model model, @ModelAttribute("notification") Notification notification) {
        final String keywords = cartQuery.getKeywords();
        if (keywords != null) {
            final List<User> userList = userService.selectByNickameOrAccount(keywords);
            final List<Long> userIdList = userList.stream().map(User::getId).toList();
            cartQuery.setUserIds(userIdList);
        }

        final PageBean<Cart> cartPageBean = cartService.selectPage(cartQuery);
        PageBean<CartVO> pageBean = cartTrans.getInstance().tPageBean2VPageBean(cartPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(cartQuery);
        pageBean.setUrl("manager/cart/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("cartQuery", cartQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/cart";
    }

    /**
     * 省份详情
     * @param
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(CartItemQuery cartItemQuery, Model model) {
        final Cart cart = cartService.selectByPrimaryKey(cartItemQuery.getCartId());
        model.addAttribute("cart", cart);

        final User user = userService.selectByPrimaryKey(cart.getUserId());
        model.addAttribute("username", user.getNickname());

        List<CartItem> cartItemList = cartItemService.selectList(cartItemQuery);
        final List<CartItemVO> cartItemVOList = cartItemList2CartItemVOList(cartItemList);
        model.addAttribute("cartItemList", cartItemVOList);

        return "manager/cart_details";
    }

    private List<CartItemVO> cartItemList2CartItemVOList(List<CartItem> cartItemList) {
        List<CartItemVO> list = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            final CartItemVO cartItemVO = cartItem2CartItemVO(cartItem);
            list.add(cartItemVO);
        }
        return list;
    }

    private CartItemVO cartItem2CartItemVO(CartItem cartItem) {
        CartItemVO cartItemVO = cartItemConverter.cart2CartVO(cartItem);

        final Goods goods = goodsService.selectByPrimaryKey(cartItem.getGoodsId());
        cartItemVO.setGoodsPic(goods.getPic());
        cartItemVO.setGoodsSellingPoint(goods.getSellingPoint());
        cartItemVO.setGoodsPrice1(goods.getPrice1());
        cartItemVO.setGoodsPrice2(goods.getPrice2());
        cartItemVO.setGoodsUnit(goods.getUnit());
        cartItemVO.setGoodsName(goods.getName());

        return cartItemVO;
    }
}
