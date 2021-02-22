/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: ToolConfig
  Author:   xingshuang
  Date:     2019/11/9
  Description:
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.github.xingshuangs.mybatis.plus.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xingshuang
 * @date 2019/11/9
 */
@Configuration
public class ToolConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
