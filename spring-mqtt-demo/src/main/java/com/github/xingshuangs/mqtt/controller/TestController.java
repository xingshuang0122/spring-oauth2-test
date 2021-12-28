package com.github.xingshuangs.mqtt.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.xingshuangs.mqtt.service.MessageRequest;
import com.github.xingshuangs.mqtt.service.MqttService;
import com.github.xingshuangs.mqtt.service.MqttServiceCallback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xingshuang
 */
@Api(tags = "同步异步请求")
@RestController
@RequestMapping("/mqtt")
@Slf4j
public class TestController {

    public static final int TIME_OUT_MAX_COUNT = 3;

    private final MqttService mqttService;

    @Autowired
    public TestController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @ApiOperation("异步请求触发方式 - triggerMqttAsync")
    @GetMapping("/trigger/async")
    public ResponseEntity triggerMqttAsync() throws JsonProcessingException, MqttException, InterruptedException {
        MessageRequest request = new MessageRequest();
        log.info("异步请求，触发mqtt调用，请求={}", request);
        MqttServiceCallback callback = this.mqttService.callAsync(request);
        int count = 0;
        while (!callback.getCompleted() && count < TIME_OUT_MAX_COUNT) {
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
        if (callback.getCompleted()) {
            log.info("异步请求，接收到数据：{}", callback.getResponse());
            return ResponseEntity.ok(callback.getResponse().getData());
        } else {
            log.error("异步请求，响应超时,{}", request);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("异步请求，请求超时");
        }
    }

    @ApiOperation("同步请求触发方式 - triggerMqttSync")
    @GetMapping("/trigger/sync")
    public ResponseEntity triggerMqttSync() throws JsonProcessingException, MqttException {
        MessageRequest request = new MessageRequest();
        log.info("同步请求，触发mqtt调用，请求={}", request);
        MqttServiceCallback callback = this.mqttService.callSync(request);
        if (callback.getCompleted()) {
            log.info("同步请求，接收到数据：{}", callback.getResponse());
            return ResponseEntity.ok(callback.getResponse().getData());
        } else {
            log.error("同步请求，响应超时,{}", request);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("同步请求，请求超时");
        }
    }
}
