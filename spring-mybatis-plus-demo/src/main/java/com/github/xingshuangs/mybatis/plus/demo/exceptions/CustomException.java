/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: CustomException
  Author:   xingshuang
  Date:     2019/4/26
  Description:
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.github.xingshuangs.mybatis.plus.demo.exceptions;


/**
 * @author xingshuang
 * @date 2019/4/26
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

}
