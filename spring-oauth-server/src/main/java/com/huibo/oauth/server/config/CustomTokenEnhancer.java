/*
  Copyright (C), 2009-2019, 江苏汇博机器人技术股份有限公司
  FileName: CustomTokenEnhancer
  Author:   xingshuang
  Date:     2019/8/28
  Description: 
  History:
  <author>          <time>          <version>          <desc>
  作者姓名           修改时间           版本号              描述
 */

package com.huibo.oauth.server.config;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xingshuang
 * @date 2019/8/28
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>(6);
//        additionalInfo.put("userInfo", authentication.getPrincipal());
        additionalInfo.put("username", ((User)authentication.getPrincipal()).getUsername());
//        additionalInfo.put("authorities", ((User)authentication.getPrincipal()).getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
