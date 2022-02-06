package com.github.xingshuangs.starter.demo.config;


import com.github.xingshuangs.starter.demo.properties.DeviceProperties;
import com.github.xingshuangs.starter.demo.service.Worker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

    @Bean
    @ConditionalOnMissingBean(Worker.class)
    public Worker worker(){
        return new Worker();
    }
}
