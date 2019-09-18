/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: SimpleRespose
  Author:   xingshuang
  Date:     2019/9/18
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.security.demo.model;


/**
 * @author xingshuang
 * @date 2019/9/18
 */
public class SimpleRespose {

    private Object content;

    public SimpleRespose(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
