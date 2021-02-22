/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: SecurityProperties
  Author:   xingshuang
  Date:     2019/9/18
  Description:
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.github.xingshuangs.security.demo.model;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xingshuang
 * @date 2019/9/18
 */
@ConfigurationProperties(prefix = "immoc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
