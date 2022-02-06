package com.github.xingshuangs.spring.configuration.config;


import com.github.xingshuangs.spring.configuration.TestInit;
import com.github.xingshuangs.starter.demo.service.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 */
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
    public TestInit testInit(){
        return new TestInit();
    }
}
