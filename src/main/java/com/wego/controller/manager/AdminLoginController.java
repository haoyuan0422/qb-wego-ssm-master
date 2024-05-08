package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.constant.WeGoConst;
import com.wego.entity.domain.Admin;
import com.wego.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    /**
     * 打开登录页面
     * @return
     */
    @GetMapping("/openLogin")
    public String openLogin(Model model, @ModelAttribute("notification") Notification notification) {
        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }
        return "manager/login";
    }

    /**
     * 登录操作
     * @param account
     * @param password
     * @param verifyCode
     * @return
     */
    @PostMapping("/doLogin")
    public String login(HttpSession session, String account, String password, String verifyCode, RedirectAttributes redirectAttributes) {
        Object verifyCode0 = session.getAttribute(WeGoConst.VERIFY_CODE);
        if (verifyCode == null || verifyCode0 == null) {
            Notification notification = new Notification(true, "校验码出错");
            redirectAttributes.addFlashAttribute("notification", notification);
            return "redirect:openLogin";
        }
        if (!verifyCode.equalsIgnoreCase(verifyCode0.toString())) {
            Notification notification = new Notification(true, "校验码出错");
            redirectAttributes.addFlashAttribute("notification", notification);
            return "redirect:openLogin";
        }

        Admin admin = adminService.login(account, password);
        if (admin != null) {
            //登录成功
            //将用户信息保存到Session中
            session.setAttribute(WeGoConst.SESSION_ADMIN, admin);
            return "/manager/index";
        }
        return "redirect:openLogin";
    }

    @GetMapping("/doLogout")
    public String doLogin(HttpSession session) {
        session.setAttribute(WeGoConst.SESSION_ADMIN, null);
        return "redirect:openLogin";
    }

}
