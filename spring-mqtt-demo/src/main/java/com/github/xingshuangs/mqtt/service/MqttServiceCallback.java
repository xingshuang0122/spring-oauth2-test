package com.github.xingshuangs.mqtt.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

/**
 * mqtt服务的回调
 *
 * @author xingshuang
 */
public class MqttServiceCallback implements IMqttMessageListener {

    /**
     * 响应对象
     */
    private MessageResponse response;

    /**
     * 请求对象
     */
    private MessageRequest request;

    /**
     * json转换对象
     */
    private ObjectMapper objectMapper;

    /**
     * mqtt客户端
     */
    private MqttClient mqttClient;

    /**
     * 是否完成
     */
    private Boolean completed;

    /**
     * 锁对象
     */
    private final Object lock = new Object();

    public MqttServiceCallback(MqttClient mqttClient, MessageRequest request, ObjectMapper objectMapper) {
        this.completed = false;
        this.response = null;
        this.mqttClient = mqttClient;
        this.request = request;
        this.objectMapper = objectMapper;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Object getLock() {
        return lock;
    }

    public MessageResponse getResponse() {
        return this.response;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String content = new String(message.getPayload(), StandardCharsets.UTF_8);
        MessageResponse messageResponse = this.objectMapper.readValue(content, MessageResponse.class);
        // id不一致则表示不是该请求的响应消息，直接返回
        if (this.request.getId() != messageResponse.getId()) {
            return;
        }
        this.response = messageResponse;
        this.completed = true;
        this.mqttClient.unsubscribe(request.getResponseTopic());
        synchronized (this.lock) {
            this.lock.notifyAll();
        }
    }
}
