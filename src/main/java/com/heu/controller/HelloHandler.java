package com.heu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: qt
 * @Date: 2020/10/31 12:12
 * @Description:
 */
@Controller
public class HelloHandler {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
