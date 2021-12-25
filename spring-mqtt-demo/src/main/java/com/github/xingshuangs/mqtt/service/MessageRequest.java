package com.github.xingshuangs.mqtt.service;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息请求对象
 *
 * @author xingshuang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequest extends MessageResponse {

    /**
     * 请求发布的主题
     */
    private String publishTopic = "request/slave/xingshuang/navigation/simple";

    /**
     * 请求消息的等级
     */
    private int publishQos = 2;
}
