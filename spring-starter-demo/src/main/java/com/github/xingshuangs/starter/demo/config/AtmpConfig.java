package com.github.xingshuangs.starter.demo.config;


import com.github.xingshuangs.starter.demo.service.Teacher;
import com.github.xingshuangs.starter.demo.service.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 */
@Configuration
public class AtmpConfig {

    @Bean
    public Teacher teacher(Worker worker) {
        return new Teacher();
    }
}
