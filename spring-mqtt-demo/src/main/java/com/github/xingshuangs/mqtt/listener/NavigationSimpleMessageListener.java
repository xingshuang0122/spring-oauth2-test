package com.github.xingshuangs.mqtt.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xingshuangs.mqtt.service.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 单独定位导航消息监听
 *
 * @author xingshuang
 */
@Slf4j
@Component
public class NavigationSimpleMessageListener implements IMqttMessageListener {

    /**
     * json转换对象
     */
    private final ObjectMapper objectMapper;

    /**
     * mqtt对象
     */
    private final MqttClient mqttClient;

    @Autowired
    public NavigationSimpleMessageListener(MqttClient mqttClient, ObjectMapper objectMapper) {
        this.mqttClient = mqttClient;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void init() {
        try {
            if (this.mqttClient.isConnected()) {
                this.mqttClient.subscribe("request/slave/xingshuang/navigation/simple", 2, this);
            }
        } catch (MqttException e) {
            log.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    private void destroy() {
        try {
            if (this.mqttClient.isConnected()) {
                this.mqttClient.unsubscribe("request/slave/xingshuang/navigation/simple");
            }
        } catch (MqttException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            String content = new String(message.getPayload(), StandardCharsets.UTF_8);
            log.info("响应方，接收到消息内容：{}", content);

            TimeUnit.SECONDS.sleep(6);
            MessageResponse messageResponse = this.objectMapper.readValue(content, MessageResponse.class);
            messageResponse.setData("响应数据!!!!");
            messageResponse.setTime(new Date());
            String json = this.objectMapper.writeValueAsString(messageResponse);
            MqttMessage mqttMessage = new MqttMessage(json.getBytes(StandardCharsets.UTF_8));
            mqttMessage.setQos(messageResponse.getResponseQos());
            this.mqttClient.publish(messageResponse.getResponseTopic(), mqttMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
