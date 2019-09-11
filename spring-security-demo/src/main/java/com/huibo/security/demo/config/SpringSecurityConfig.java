/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: SpringSecurityConfig
  Author:   xingshuang
  Date:     2019/9/11
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.security.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xingshuang
 * @date 2019/9/11
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
