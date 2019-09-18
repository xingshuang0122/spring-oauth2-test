/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: PageExchange
  Author:   xingshuang
  Date:     2019/9/12
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.security.demo.controller;


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
