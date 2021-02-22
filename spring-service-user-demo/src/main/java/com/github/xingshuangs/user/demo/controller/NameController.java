package com.github.xingshuangs.user.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShuangPC
 * @date 2020/5/7
 */
@RestController
public class NameController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('admin')")
    public String getName() {
        return "Hello Nacos!";
    }

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('admin1')")
    public String getName1() {
        return "Hello Nacos+++++!";
    }
}
