package com.github.xingshuangs.user.demo.dto;


import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义Authentication对象，主要是用于登录使用
 *
 * @author xingshuang
 * @date 2020/5/28
 */
@Data
public class CustomAuthentication implements Authentication {

    private Boolean authenticated = true;
    private String credentials;
    private String principal;

    public CustomAuthentication(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.principal;
    }
}
