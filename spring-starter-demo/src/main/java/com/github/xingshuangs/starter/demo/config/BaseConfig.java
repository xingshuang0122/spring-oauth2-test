package com.github.xingshuangs.starter.demo.config;


import com.github.xingshuangs.starter.demo.properties.DeviceProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 */
@Configuration
public class BaseConfig {

    @ConditionalOnProperty(prefix = DeviceProperties.PREFIX, name = "open", havingValue = "true")
    @Bean
    public DeviceProperties deviceProperties() {
        return new DeviceProperties();
    }

    @ConditionalOnProperty(prefix = DeviceProperties.PREFIX, name = "open", havingValue = "false", matchIfMissing = true)
    @Bean
    public DeviceProperties deviceProperties1() {
        return new DeviceProperties();
    }
}
