package com.huibo.mybatis.plus.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class SpringMybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisPlusDemoApplication.class, args);
    }

}
