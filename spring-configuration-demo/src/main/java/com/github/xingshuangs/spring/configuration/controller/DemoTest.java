package com.github.xingshuangs.spring.configuration.controller;


import com.github.xingshuangs.spring.configuration.CustomProperties;
import com.github.xingshuangs.starter.demo.controller.HelloWorld;
import com.github.xingshuangs.starter.demo.properties.DeviceProperties;
import com.github.xingshuangs.starter.demo.service.Box;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingshuang
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class DemoTest {

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private HelloWorld helloWorld;

    @Autowired
    private Box box;

    @Autowired
    private DeviceProperties deviceProperties;

    @GetMapping("/demo")
    public ResponseEntity triggerMqttSync() {
        log.info("控制层={}", customProperties);
        log.info("hello={}", helloWorld);
        log.info("box={}", box);
        log.info("device={}", deviceProperties);
        return ResponseEntity.ok(customProperties);
    }
}
