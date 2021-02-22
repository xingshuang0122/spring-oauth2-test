/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: TestData
  Author:   xingshuang
  Date:     2019/9/11
  Description:
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

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
