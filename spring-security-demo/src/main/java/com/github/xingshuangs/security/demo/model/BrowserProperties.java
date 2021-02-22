/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: BrowserProperties
  Author:   xingshuang
  Date:     2019/9/18
  Description:
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.github.xingshuangs.security.demo.model;


/**
 * @author xingshuang
 * @date 2019/9/18
 */
public class BrowserProperties {

    private String loginPage = "/signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
