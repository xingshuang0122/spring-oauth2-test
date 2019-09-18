/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: SecurityCoreConfig
  Author:   xingshuang
  Date:     2019/9/18
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.security.demo.config;


import com.huibo.security.demo.model.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 * @date 2019/9/18
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
