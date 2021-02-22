package com.github.xingshuangs.security.demo.config;


import com.github.xingshuangs.security.demo.model.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 * @date 2019/9/18
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
