package com.github.xingshuangs.user.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义OAuth2的客户端属性
 * 示例：
 * client-details:
 * client-id: client11
 * client-secret: 123456
 * grant-type: password
 *
 * @author xingshuang
 * @date 2020/5/30
 */
@Configuration
@ConfigurationProperties(prefix = "client-details")
@Data
public class CustomClientProperties {

    /**
     * clientId，类似用户名
     */
    private String clientId = "client";

    /**
     * client secret，类似密码
     */
    private String clientSecret = "secret";

    /**
     * grant type，授权方式
     */
    private String grantType = "password";
}
