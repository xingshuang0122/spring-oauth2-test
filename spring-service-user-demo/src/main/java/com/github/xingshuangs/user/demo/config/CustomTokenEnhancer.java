package com.github.xingshuangs.user.demo.config;

import com.github.xingshuangs.user.demo.common.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * Token加强，添加用户的额外信息
 *
 * @author xingshuang
 * @date 2020/5/30
 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if ((accessToken instanceof DefaultOAuth2AccessToken)) {
            DefaultOAuth2AccessToken enhancerToken = ((DefaultOAuth2AccessToken) accessToken);
            // 提取用户信息并添加到oAuth2AccessToken中
            CustomUser user = (CustomUser) authentication.getUserAuthentication().getPrincipal();
            try {
                enhancerToken.setAdditionalInformation(user.convert2Map());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
            return enhancerToken;
        }
        return accessToken;
    }
}
