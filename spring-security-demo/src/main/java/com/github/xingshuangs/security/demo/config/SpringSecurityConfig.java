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

package com.github.xingshuangs.security.demo.config;


import com.github.xingshuangs.security.demo.model.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author xingshuang
 * @date 2019/9/11
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        http.formLogin()
                // 修改登录页面
                .loginPage("/authentication/require")
                // 修改登录请求的处理路径
                .loginProcessingUrl("/authentication/form")
                .successHandler(this.authenticationSuccessHandler)
                .failureHandler(this.authenticationFailureHandler)
                .and()
                .authorizeRequests()
                // 过滤登录页面
                .antMatchers("/authentication/require",this.securityProperties.getBrowser().getLoginPage()).permitAll()
                // 其他都需要授权
                .anyRequest().authenticated()
                .and()
                // 跨站防护请求
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
