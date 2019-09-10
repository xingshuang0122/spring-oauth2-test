/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: AuthorizationServerConfig
  Author:   ShuangPC
  Date:     2019/8/28
  Description:
  History:
  <author>         <time>          <version>          <desc>
  作者姓名         修改时间           版本号             描述
 */

package com.huibo.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;

/**
 * @author ShuangPC
 * @date 2019/8/28
 */
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));

        endpoints.tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }


    /**
     * 使用JdbcClientDetailsService客户端详情服务
     * clients.withClientDetails(new JdbcClientDetailsService(dataSource));
     *
     * @param clients 客户端对象
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 基于内存存储令牌
        clients.inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret(bCryptPasswordEncoder.encode("secret"))
                // 该client允许的授权类型
                .authorizedGrantTypes("password")
                // 允许的授权范围
                .scopes("app")
                //登录后绕过批准询问(/oauth/confirm_access)
                .autoApprove(true);
    }

    /**
     * 授权服务器的安全配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // 允许客户端发送表单来进行权限认证来获取令牌
                .allowFormAuthenticationForClients()
                .passwordEncoder(bCryptPasswordEncoder)
                // 允许所有资源服务器访问公钥端点（/oauth/token_key）
                .tokenKeyAccess("permitAll()")
                // 只允许验证用户访问令牌解析端点（/oauth/check_token）
                .checkTokenAccess("isAuthenticated()");
    }
}
