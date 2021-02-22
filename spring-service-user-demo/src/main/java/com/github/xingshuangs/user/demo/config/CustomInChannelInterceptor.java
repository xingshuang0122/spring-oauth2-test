package com.github.xingshuangs.user.demo.config;


import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

/**
 * @author xingshuang
 * @date 2020/6/2
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class CustomInChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader("Authorization");
            if (!Strings.isNullOrEmpty(token)) {
                OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(oAuth2Authentication);
                accessor.setUser(oAuth2Authentication);
            }
        } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            log.info("订阅消息，sessionId = {}，subId = {}，des = {}", accessor.getSessionId(), accessor.getSubscriptionId(), accessor.getDestination());
        } else if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) {
            log.info("取消订阅，sessionId = {}，subId = {}, des = {}", accessor.getSessionId(), accessor.getSubscriptionId(), accessor.getDestination());
        }
        return message;
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
    }
}
