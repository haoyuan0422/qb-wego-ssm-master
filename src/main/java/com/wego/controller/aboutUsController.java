package com.wego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutUsController {

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }
}
