package com.github.xingshuangs.mqtt.service;


import lombok.Data;

import java.util.Date;

/**
 * 消息响应对象
 *
 * @author xingshuang
 */
@Data
public class MessageResponse {

    /**
     * 消息id
     */
    private long id = System.nanoTime();

    /**
     * 消息主题
     */
    private String responseTopic = "response/slave/xingshuang/navigation/simple";

    /**
     * 消息等级
     */
    private int responseQos = 2;

    /**
     * 消息内容
     */
    private String data = "hello world";

    /**
     * 时间
     */
    private Date time = new Date();
}
