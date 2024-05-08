package com.wego.controller;

import com.wego.constant.WeGoConst;
import com.wego.converter.GoodsConverter;
import com.wego.entity.domain.Category;
import com.wego.entity.domain.Goods;
import com.wego.entity.vo.CartItemVO;
import com.wego.service.CategoryService;
import com.wego.service.GoodsService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端购物车处理器
 * @author hc
 */
@Controller("CartControllerFront")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsConverter goodsConverter;

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取购物车
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "cart";
    }

    /**
     * 清空购物车
     * @param session
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/clearCart")
    public String clear(HttpSession session)   {
        session.setAttribute(WeGoConst.CART, new HashMap<Goods, Integer>(0));
        return "cart";
    }

    /**
     * 删除购物项
     * @param goodsId
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("deleteCartItem")
    public String deleteById(Long goodsId,HttpSession session){
        CartItemVO cartItemVO = new CartItemVO();
        cartItemVO.setGoodsId(goodsId);
        Map<CartItemVO, Integer> cart = (Map<CartItemVO, Integer>) session.getAttribute(WeGoConst.CART);
        if (cart != null && cart.containsKey(cartItemVO)) {
            cart.remove(cartItemVO);
        }
        return "cart";
    }

    /**
     * 将用户选择的商品加入购物车
     * @param goodsId
     * @param amount
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/add")
    public String add(Long goodsId, Integer amount, HttpServletRequest request) {
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        if (goods != null) {
            //获取用户购买商品的类别编号
            Long categoryId = goods.getCategoryId();
            if (categoryId != null) {
                // 获取上下文对象
                ServletContext servletContext = request.getServletContext();
                servletContext.setAttribute("cid", categoryId);
                Category category = categoryService.selectByPrimaryKey(categoryId);
                servletContext.setAttribute("cname", category.getName());
            }

            CartItemVO cartItemVO = goodsConverter.goods2CartItemVO(goods);
            //设置总金额
            cartItemVO.setMoney(cartItemVO.getGoodsPrice2().multiply(new BigDecimal(amount)));

            Map<CartItemVO, Integer> cart = (Map<CartItemVO, Integer>) request.getSession().getAttribute(WeGoConst.CART);
            if (cart == null) {
                cart = new HashMap<>(1);
                request.getSession().setAttribute(WeGoConst.CART, cart);
            }

            //将买到的商品放入购物车：如果没有直接放入，如果用数量增加
            if (cart.containsKey(cartItemVO)) {
                cart.put(cartItemVO, cart.get(cartItemVO) + amount);
            } else {
                cart.put(cartItemVO, amount);
            }
            //将购物车传递到前端页面进行显示
            //request.setAttribute(WeGoConst.CART, cart);
        }
        return "cart";
    }

}
