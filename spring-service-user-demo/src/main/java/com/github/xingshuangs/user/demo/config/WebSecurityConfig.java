package com.github.xingshuangs.user.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ShuangPC
 * @date 2020/5/28
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置http访问控制
     *
     * @param http http安全配置
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // permitAll 没有绕过spring security，其中包含了登录的以及匿名的
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors();
    }

    @Override
    public void configure(WebSecurity web) {
        // ignore 是完全绕过了spring security的所有filter，相当于不走spring security
//        web.ignoring()
//                .antMatchers(HttpMethod.OPTIONS)
//                .antMatchers("/common/**");
    }
}
