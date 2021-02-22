package com.github.xingshuangs.user.demo.service;

import com.github.xingshuangs.user.demo.common.CustomUser;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShuangPC
 * @date 2020/05/10
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        // 获取用户权限列表
        List<GrantedAuthority> authorities = Lists.newArrayList();
        // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
        authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        authorities.add(new SimpleGrantedAuthority("admin1"));
        return new CustomUser(username, password, authorities);
    }
}
