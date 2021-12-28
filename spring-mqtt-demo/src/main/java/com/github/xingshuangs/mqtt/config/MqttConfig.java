package com.github.xingshuangs.mqtt.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Mqtt的配置
 *
 * @author xingshuang
 */
@Configuration
@Slf4j
public class MqttConfig {

    /**
     * 连接地址
     */
    private String host = "tcp://114.55.175.207:1883";

    /**
     * 连接id，注意不要和已有的连接id重复
     */
    private String clientId = String.valueOf(System.currentTimeMillis());
    ;

    /**
     * 用户名
     */
    private String username = "xing";

    /**
     * 密码
     */
    private String password = "123456";

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public MqttClient mqttClient() throws MqttException {
        try {
            MqttClient client = new MqttClient(host, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            // 清空session
            options.setCleanSession(true);
            // 用户名
            options.setUserName(this.username);
            // 密码
            options.setPassword(this.password.toCharArray());
            // 自动重连
            options.setAutomaticReconnect(true);
            // 设置超时时间
            options.setConnectionTimeout(10);
            // 设置会话心跳时间
            options.setKeepAliveInterval(20);
            // 遗嘱模式
//            options.setWill(topic, this.getOnlineStatusString(false, robotBaseInfo).getBytes(StandardCharsets.UTF_8), 1, true);
            // 创建连接
            boolean result = false;
            for (int i = 0; i < 1; i++) {
                result = this.doConnect(client, options);
                if (result) {
                    break;
                } else {
                    TimeUnit.SECONDS.sleep(20);
                }
            }
//            client.connect(options);
            // 连接成功之后发送一次状态，若想清空保留数据，只需发送空数据，同时retained为true即可(监听到服务器的参数之后再推送机器人的相关参数)
//            client.publish(topic, this.getOnlineStatusString(true, robotBaseInfo).getBytes(StandardCharsets.UTF_8), 1, true);
            if (result) {
                log.debug("连接Mqtt服务器成功，地址={}", host);
            } else {
                log.error("连接Mqtt服务器失败，地址={}", host);
            }
            return client;
        } catch (MqttException | InterruptedException e) {
            log.error("MQTT连接失败" + e.getMessage(), e);
            return new MqttClient(host, clientId, new MemoryPersistence());
        }
    }

    private boolean doConnect(MqttClient client, MqttConnectOptions options) {
        try {
            client.connect(options);
            return true;
        } catch (MqttException e) {
            log.error(e.getMessage());
            return false;
        }
    }

}
