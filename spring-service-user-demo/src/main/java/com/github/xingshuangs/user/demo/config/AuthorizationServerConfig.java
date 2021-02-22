package com.github.xingshuangs.user.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author ShuangPC
 * @date 2020/5/28
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * token的有效时间6个小时
     */
    private static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;

    /**
     * token的刷新时间1天
     */
    private static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;

    /**
     * redis中tokenStore的键名前缀
     */
    private static final String REDIS_TOKEN_STORE_PREFIX = "auth-token:";

    /**
     * client信息
     */
    @Autowired
    private CustomClientProperties clientProperties;

    /**
     * 用户认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * userDetails服务
     */
    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 密码加密器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * tokenStore对象
     */
    @Autowired
    private TokenStore tokenStore;

    /**
     * token服务
     */
    @Autowired
    private DefaultTokenServices tokenService;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置认证管理器
        endpoints.authenticationManager(authenticationManager)
                // 配置用户服务
                .userDetailsService(userDetailsService)
                // 配置token存储的服务与位置
                .tokenServices(this.tokenService)
                // 配置token的存储
                .tokenStore(this.tokenStore);
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
                .withClient(this.clientProperties.getClientId())
                // client_secret
                .secret(passwordEncoder.encode(this.clientProperties.getClientSecret()))
                // 该client允许的授权类型
                .authorizedGrantTypes(this.clientProperties.getGrantType())
                // token的有效时间
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                // token的刷新时间
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
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
        // 允许客户端发送表单来进行权限认证来获取令牌
        security.allowFormAuthenticationForClients()
                // 允许所有资源服务器访问公钥端点（/oauth/token_key）
                .tokenKeyAccess("permitAll()")
                // 配置密码加密工具
                .passwordEncoder(passwordEncoder)
                // 只允许用户访问令牌解析端点（/oauth/check_token）
                .checkTokenAccess("permitAll()");
    }

    /**
     * tokenStore的存储设置，这里采用redisTokenStore
     *
     * @return TokenStore的对象
     */
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        //使用redis存储token
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置redis token存储中的前缀
        redisTokenStore.setPrefix(REDIS_TOKEN_STORE_PREFIX);
        return redisTokenStore;
    }

    /**
     * 配置tokenService的操作服务
     *
     * @return DefaultTokenServices对象
     */
    @Bean
    public DefaultTokenServices tokenService(TokenEnhancer tokenEnhancer) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 配置token存储
        tokenServices.setTokenStore(this.tokenStore);
        // 开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        tokenServices.setSupportRefreshToken(true);
        // 复用refresh_token
        tokenServices.setReuseRefreshToken(true);
        // token有效期
        tokenServices.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        // refresh_token有效期
        tokenServices.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        // 设置token加强
        tokenServices.setTokenEnhancer(tokenEnhancer);
        return tokenServices;
    }

    /**
     * BCryptPasswordEncoder密码加密工具
     *
     * @return BCryptPasswordEncoder对象
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
}
