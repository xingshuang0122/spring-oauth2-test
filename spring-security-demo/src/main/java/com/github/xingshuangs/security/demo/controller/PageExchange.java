package com.github.xingshuangs.security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xingshuang
 * @date 2019/9/12
 */
@Controller
public class PageExchange {

    @RequestMapping("/signIn.html")
    public String signIn(){
        return "signIn";
    }

    @RequestMapping("/demo-signIn.html")
    public String signInDemo(){
        return "demo-signIn";
    }
}
