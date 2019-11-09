/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: GlobalExceptionHandler
  Author:   ShuangPC
  Date:     2019/2/20
  Description: 全局异常处理
  History:
  <author>         <time>          <version>          <desc>
  作者姓名         修改时间           版本号             描述
 */

package com.huibo.mybatis.plus.demo.config;

import com.huibo.mybatis.plus.demo.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author ShuangPC
 * @date 2019/2/20
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时的异常
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult customException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.fail();
    }

    /**
     * 异常
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return ResponseResult.fail();
    }
}
