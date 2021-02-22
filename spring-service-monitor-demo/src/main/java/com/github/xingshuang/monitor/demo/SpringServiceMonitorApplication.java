package com.github.xingshuang.monitor.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class SpringServiceMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServiceMonitorApplication.class, args);
    }

}
