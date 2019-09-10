package com.huibo.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SpringResourceServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringResourceServerDemoApplication.class, args);
    }

}
