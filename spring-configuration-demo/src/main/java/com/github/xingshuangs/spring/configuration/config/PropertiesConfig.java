package com.github.xingshuangs.spring.configuration.config;


import com.github.xingshuangs.spring.configuration.CustomProperties;
import com.github.xingshuangs.starter.demo.properties.DeviceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用了EnableConfigurationProperties注解生效,并且会自动将这个类注入到 IOC 容器中
 * @author xingshuang
 */
@Slf4j
@EnableConfigurationProperties()
@Configuration
public class PropertiesConfig {

    @Bean
    public CustomProperties customProperties() {
        CustomProperties customProperties = new CustomProperties();
        log.info("测试1={}", customProperties);
        return customProperties;
    }

//    @Bean
//    public DeviceProperties deviceProperties() {
//        return new DeviceProperties();
//    }
}
