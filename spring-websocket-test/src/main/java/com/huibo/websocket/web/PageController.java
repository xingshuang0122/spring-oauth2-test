/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: PageController
  Author:   xingshuang
  Date:     2019/8/31
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.websocket.web;


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
