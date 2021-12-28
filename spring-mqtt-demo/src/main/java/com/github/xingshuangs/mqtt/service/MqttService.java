package com.github.xingshuangs.mqtt.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * mqtt调用服务
 *
 * @author xingshuang
 */
@Slf4j
@Service
public class MqttService {

    /**
     * mqtt对象
     */
    private final MqttClient mqttClient;

    /**
     * json转换对象
     */
    private final ObjectMapper objectMapper;

    /**
     * 超时时间
     */
    private long timeout = 10000L;

    @Autowired
    public MqttService(MqttClient mqttClient, ObjectMapper objectMapper) {
        this.mqttClient = mqttClient;
        this.objectMapper = objectMapper;
    }

    /**
     * 同步调用
     *
     * @param request 请求对象
     * @return 回调对象，包含消息响应内容
     * @throws JsonProcessingException json对象
     * @throws MqttException           mqtt异常
     */
    public MqttServiceCallback callSync(MessageRequest request) throws JsonProcessingException, MqttException {
        return this.call(request, true, this.timeout);
    }

    /**
     * 异步调用
     *
     * @param request 请求对象
     * @return 回调对象，包含消息响应内容
     * @throws JsonProcessingException json对象
     * @throws MqttException           mqtt异常
     */
    public MqttServiceCallback callAsync(MessageRequest request) throws JsonProcessingException, MqttException {
        return this.call(request, false, this.timeout);
    }

    /**
     * 异步调用
     *
     * @param request 请求对象
     * @param timeout 超时时间
     * @return 回调对象，包含消息响应内容
     * @throws JsonProcessingException json对象
     * @throws MqttException           mqtt异常
     */
    public MqttServiceCallback callAsync(MessageRequest request, long timeout) throws JsonProcessingException, MqttException {
        return this.call(request, false, timeout);
    }

    /**
     * 底层调用
     *
     * @param request 请求对象
     * @param sync    是否同步
     * @param timeout 超时时间
     * @return 回调对象，包含消息响应内容
     * @throws JsonProcessingException json对象
     * @throws MqttException           mqtt异常
     */
    public MqttServiceCallback call(MessageRequest request, boolean sync, long timeout) throws JsonProcessingException, MqttException {
        String json = this.objectMapper.writeValueAsString(request);
        MqttMessage mqttMessage = new MqttMessage(json.getBytes(StandardCharsets.UTF_8));
        mqttMessage.setQos(request.getPublishQos());
        this.mqttClient.publish(request.getPublishTopic(), mqttMessage);

        MqttServiceCallback callback = new MqttServiceCallback(this.mqttClient, request, this.objectMapper);
        this.mqttClient.subscribe(request.getResponseTopic(), callback);
        if (sync) {
            try {
                synchronized (callback.getLock()) {
                    callback.getLock().wait(timeout);
                }
            } catch (InterruptedException e) {
            }
        }
        return callback;
    }
}
