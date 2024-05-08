package com.wego.ex.handler;

import com.wego.bean.Notification;
import com.wego.ex.GlobalException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 全局异常处理器
 * @author hc
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public String globalExceptionHandler(RedirectAttributes redirectAttributes, GlobalException e) {
        //将异常信息设置如modelAndView
        Notification notification = new Notification(true, e.getMsg());
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:" + e.getUrl();
    }

    /**
     * 用来处理外键关联异常
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String dataIntegrityViolationExceptionHandler(RedirectAttributes redirectAttributes) {
        //将异常信息设置如modelAndView
        Notification notification = new Notification(true, "后台数据库操作失败，请联系管理员");
        redirectAttributes.addFlashAttribute("notification", notification);
        return "redirect:list";
    }

}
