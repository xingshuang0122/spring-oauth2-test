/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: MyUserdetailInfo
  Author:   xingshuang
  Date:     2019/9/11
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.security.demo.model;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author xingshuang
 * @date 2019/9/11
 */
@Slf4j
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public MyUserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("根据用户名查询用户信息");
        return new User(username, this.passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
