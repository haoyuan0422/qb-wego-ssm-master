package com.wego.controller;

import com.wego.constant.WeGoConst;
import com.wego.entity.domain.Address;
import com.wego.entity.vo.CartItemVO;
import com.wego.entity.vo.UserSessionVO;
import com.wego.service.AddressService;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("OrderControllerFront")
@RequestMapping("/controller")
public class OrderController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        Map<CartItemVO, Integer> cart = (Map<CartItemVO, Integer>) session.getAttribute(WeGoConst.CART);
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        Object userObj = session.getAttribute(WeGoConst.SESSION_USER);
        if (userObj == null) {
            return "redirect:user/login";
        }
        UserSessionVO user = (UserSessionVO) userObj;
        List<Address> addressList = null;
        addressList = addressService.selectList(Address.builder()
                        .userId(user.getId())
                .build());
        model.addAttribute("addressList", addressList);

        return "order";
    }

}
