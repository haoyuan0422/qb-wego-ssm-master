package com.wego.controller;

import com.wego.common.bean.ResultBean;
import com.wego.common.utils.ResultBeanUtil;
import com.wego.common.utils.VerifyCodeUtil;
import com.wego.constant.WeGoConst;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class VerifyCodeController {

    /**
     * 获取校验码
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute(WeGoConst.VERIFY_CODE, verifyCode);
        //验证码存入图片
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtil.outputImage(105, 45, outputStream, verifyCode);
    }

    /**
     * 校验验证码
     * @param verifyCode
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/checkVerifyCode")
    public ResultBean checkVerifyCode(String verifyCode, HttpSession session) {
        Object verifyCode0 = session.getAttribute(WeGoConst.VERIFY_CODE);
        if (verifyCode0 == null) {
            return ResultBeanUtil.failure(400, "服务器端出错，请刷新页面重试");
        }
        if (verifyCode == null) {
            return ResultBeanUtil.failure(400, "验证码不能为空");
        }
        if (!verifyCode.equalsIgnoreCase(verifyCode0.toString())) {
            return ResultBeanUtil.failure(400, "验证码不正确");
        }

        return ResultBeanUtil.success(200, "验证码正确");
    }
}
