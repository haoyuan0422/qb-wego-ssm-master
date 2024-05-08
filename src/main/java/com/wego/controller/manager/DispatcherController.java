package com.wego.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 请求转发控制器
 * @author: hc
 * @date: 2023/7/3
 */
@Controller
public class DispatcherController {
    @GetMapping("/managerIndex")
    public String managerIndex() {
        return "manager/index";
    }

    @GetMapping("/manager/statistic")
    public String managerStatistic() {
        return "manager/statistic";
    }
}
