package com.github.xingshuangs.resource.server.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面切换
 *
 * @author xingshuang
 * @date 2019/9/10
 */
@Controller
public class PageSwitch {

    @RequestMapping("/test")
    public String change2Test(){
        return "Test";
    }
}
