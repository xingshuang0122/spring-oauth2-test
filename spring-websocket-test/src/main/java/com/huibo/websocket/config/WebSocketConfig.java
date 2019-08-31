/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: WebSocketConfig
  Author:   xingshuang
  Date:     2019/8/31
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.websocket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author xingshuang
 * @date 2019/8/31
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
