package com.huibo.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableWebSecurity
@EnableAuthorizationServer
@SpringBootApplication
public class SpringOauthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOauthServerApplication.class, args);
    }

}
