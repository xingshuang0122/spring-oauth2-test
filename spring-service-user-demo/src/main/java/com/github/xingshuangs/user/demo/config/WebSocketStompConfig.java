package com.github.xingshuangs.user.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author ShuangPC
 * @date 2019/4/26
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 入通道的拦截器
     */
    @Qualifier("customInChannelInterceptor")
    @Autowired
    private ChannelInterceptor channelInInterceptor;

    /**
     * 出通道的拦截器
     */
    @Qualifier("customOutChannelInterceptor")
    @Autowired
    private ChannelInterceptor channelOutInterceptor;

    /**
     * 注册stomp的端点（必须）
     * 允许使用socketJs方式访问，访问点为websocket-server
     * 在网页上我们就可以通过这个链接 http://localhost:8090/websocket-server 来和服务器的WebSocket连接
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-server")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 配置信息代理（必须）
     * 全局使用的消息前缀（客户端订阅路径上会体现出来）
     * registry.setApplicationDestinationPrefixes("/ms");
     * 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
     * registry.setUserDestinationPrefix("/user/");
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称
        registry.enableSimpleBroker("/topic", "/user");
        registry.setUserDestinationPrefix("/user");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(this.channelInInterceptor);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(this.channelOutInterceptor);
    }

}
