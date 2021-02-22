package com.github.xingshuang.monitor.demo.config;


import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class CustomChannelInterceptor implements ChannelInterceptor {

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
        }
        return message;
    }
}
