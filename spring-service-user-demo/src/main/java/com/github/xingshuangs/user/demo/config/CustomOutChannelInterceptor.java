package com.github.xingshuangs.user.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author ShuangPC
 * @date 2020/7/8
 */
@Slf4j
@Component
public class CustomOutChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message;
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
        if (ex != null) {
            return;
        }
        SimpMessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, SimpMessageHeaderAccessor.class);
        if (accessor == null || accessor.getMessageType() == null) {
            return;
        }
        if (SimpMessageType.CONNECT_ACK.equals(accessor.getMessageType())) {
            // 检测到disconnect，将用户登出
            Principal authentication = accessor.getUser();
            if (authentication == null) {
                log.error("不存在用户信息");
                return;
            }
            log.info("用户[{}]通过websocket连入系统", authentication.getName());
        } else if (SimpMessageType.DISCONNECT_ACK.equals(accessor.getMessageType())) {
            // 检测到disconnect，将用户登出
            Principal authentication = accessor.getUser();
            if (authentication == null) {
                log.error("不存在用户信息");
                return;
            }
            OAuth2AccessToken accessToken = this.defaultTokenServices.getAccessToken((OAuth2Authentication) authentication);
            if (accessToken == null) {
                log.error("该用户不存在accessToken");
                return;
            }
            this.defaultTokenServices.revokeToken(accessToken.getValue());
            log.info("用户[{}]因websocket断开连接而触发登出操作", authentication.getName());
        }
    }
}
