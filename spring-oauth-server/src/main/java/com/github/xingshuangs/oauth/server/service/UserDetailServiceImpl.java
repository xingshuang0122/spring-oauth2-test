package com.github.xingshuangs.oauth.server.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShuangPC
 * @date 2019/8/28
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        // 获取用户权限列表
        List<GrantedAuthority> authorities = Lists.newArrayList();
        // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");
        authorities.add(authority);
        return new User(s, password, authorities);
    }
}
