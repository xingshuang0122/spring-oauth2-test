package com.github.xingshuangs.spring.configuration.config;


import com.github.xingshuangs.spring.configuration.CustomProperties;
import com.github.xingshuangs.spring.configuration.TestInit;
import com.github.xingshuangs.starter.demo.service.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 */
@Slf4j
@Configuration
public class DemoConfig {

    @Bean
    public Worker worker() {
        Worker worker = new Worker();
        worker.setName("xing");
        worker.setAddress("demo");
        return worker;
    }

    @Bean
    public TestInit testInit(CustomProperties customProperties) {
        log.info("测试：{}", customProperties);
        return new TestInit();
    }
}
