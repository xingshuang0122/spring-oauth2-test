package com.github.xingshuangs.starter.demo.controller;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author xingshuang
 */
@Data
@Component
public class HelloWorld {

    private String name = "hello";
    private Integer age = 11;
}
