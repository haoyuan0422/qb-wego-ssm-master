package com.wego.controller;

import com.wego.common.bean.ResultBean;
import com.wego.common.utils.Base64Util;
import com.wego.common.utils.ResultBeanUtil;
import com.wego.constant.WeGoConst;
import com.wego.converter.UserConverter;
import com.wego.entity.domain.User;
import com.wego.entity.vo.UserSessionVO;
import com.wego.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端用户处理器
 * @author hc
 */
@Controller("UserControlerFront")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    /**
     * 打开注册页面
     * @return
     */
    @GetMapping("/openRegister")
    public String openRegister() {
        return "register";
    }

    /**
     * 用户注册
     * @param account
     * @param password1
     * @param password2
     * @param verifyCode
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/doRegister")
    public String doRegister(String account, String password1, String password2, String verifyCode, HttpSession session, Model model) {
        String msg = "";
        //获取前端传递过来的数据并校验
        boolean accountExist = userService.accountExist(account);
        if (accountExist) {
            msg += "账户已经存在\n";
        }
        if (password1 != null && !password1.equals(password2)) {
            msg += "两次输出的密码不一致\n";
        }
        if (verifyCode != null) {
            Object verifyCode0 = session.getAttribute("verifyCode");
            if (verifyCode0 == null) {
                msg += "服务器端出错，请刷新页面重试\n";
            } else if (!verifyCode.equalsIgnoreCase(verifyCode0.toString())) {
                msg += "验证码不正确\n";
            }

        }
        //如果检验出错
        if (msg.length() > 0) {
            model.addAttribute("resultBean", ResultBeanUtil.failure(400, msg));
            return "redirect:openRegister";
        }

        boolean insertRes = userService.insert(User.builder()
                .account(account)
                //对前端传递过来的密码进行加密
                .password(Base64Util.encode(password1))
                .state(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build());
        //注册成功打开登录页面
        if (insertRes) {
            model.addAttribute("resultBean", ResultBeanUtil.failure(200, "注册成功"));
            return "redirect:openLogin";
        }
        //失败打开注册页面
        model.addAttribute("resultBean", ResultBeanUtil.failure(400, "注册失败"));
        return "redirect:openRegister";
    }

    /**
     * 打开登录页面
     * @return
     */
    @GetMapping("openLogin")
    String openLogin() {
        return "login";
    }

    /**
     * 登录
     *
     * @return 登录成功打开前端首页，失败继续回到登录页面
     */
    @PostMapping("doLogin")
    String doLogin(String account, String password, HttpSession session) {
        password = Base64Util.encode(password);
        User user = userService.login(account, password);
        if (user != null) {
            UserSessionVO userSessionVO = userConverter.user2UserSessionVO(user);
            //将用户信息保存到Session
            session.setAttribute(WeGoConst.SESSION_USER, userSessionVO);
            //打开前端首页
            return "redirect:/index";
        }
        return "redirect:openLogin";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/doLogout")
    public String logout(HttpSession session) {
        session.removeAttribute(WeGoConst.SESSION_USER);
        //前台首页
        return "index";
    }

    /**
     * 校验账户是否存在
     * @param account
     * @throws SQLException
     * @throws IOException
     * @return
     */
    @ResponseBody
    @GetMapping("/checkAccount")
    public ResultBean checkAccount(String account) {
        boolean exist = userService.accountExist(account);
        if (exist) {
            return ResultBeanUtil.success(200, "账户已经存在，请重新注册");
        } else {
            return ResultBeanUtil.failure(400, "账户不存在，可以使用");
        }
    }
}
