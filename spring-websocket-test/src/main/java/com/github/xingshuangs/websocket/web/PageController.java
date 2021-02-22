package com.github.xingshuangs.websocket.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xingshuang
 * @date 2019/8/31
 */
@Controller
public class PageController {

    @RequestMapping("/client")
    public String client() {
        return "client";
    }
}
