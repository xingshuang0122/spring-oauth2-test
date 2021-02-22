package com.github.xingshuangs.security.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingshuang
 * @date 2019/9/11
 */
@RestController
public class TestData {

    @RequestMapping("/test")
    public String getData() {
        return "hello world";
    }
}
