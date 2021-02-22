/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: PageSwitch
  Author:   xingshuang
  Date:     2019/9/10
  Description: 页面切换
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

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
