package com.github.xingshuangs.user.demo.controller;


import com.github.xingshuangs.common.utils.HttpUtils;
import com.github.xingshuangs.user.demo.config.CustomClientProperties;
import com.github.xingshuangs.user.demo.dto.CustomAuthentication;
import com.github.xingshuangs.user.demo.dto.LoginInfoDto;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author xingshuang
 * @date 2020/5/28
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * token的节点
     */
    private final TokenEndpoint tokenEndpoint;

    /**
     * 默认的token服务
     */
    private final DefaultTokenServices defaultTokenServices;

    /**
     * client的信息
     */
    private final CustomClientProperties clientProperties;

    /**
     * token加强
     */
    private final TokenEnhancer tokenEnhancer;

    /**
     * clientDetailsService对象
     */
    private final ClientDetailsService clientDetailsService;

    @Autowired
    public CommonController(TokenEndpoint tokenEndpoint,
                            DefaultTokenServices defaultTokenServices,
                            CustomClientProperties clientProperties,
                            TokenEnhancer tokenEnhancer,
                            ClientDetailsService clientDetailsService) {
        this.tokenEndpoint = tokenEndpoint;
        this.defaultTokenServices = defaultTokenServices;
        this.clientProperties = clientProperties;
        this.tokenEnhancer = tokenEnhancer;
        this.clientDetailsService = clientDetailsService;
    }

    /**
     * 登录接口
     *
     * @param loginInfo 用户名 + 密码
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginInfoDto loginInfo) throws HttpRequestMethodNotSupportedException {
        CustomAuthentication authentication = new CustomAuthentication(this.clientProperties.getClientId(), this.clientProperties.getClientSecret());
        Map<String, String> params = Maps.newHashMap();
        params.put("grant_type", this.clientProperties.getGrantType());
        params.put("username", loginInfo.getUsername());
        params.put("password", loginInfo.getPassword());

        //<editor-fold desc="这部分用于控制一个账号同时只能一个用户使用，登录之后，别人将无法再登录，若不想用这部分功能，注释即可">
        // 核心：根据OAuth2Authentication获取对应的token，通过TokenRequest获取OAuth2Authentication
        ClientDetails clientDetails = this.clientDetailsService.loadClientByClientId(this.clientProperties.getClientId());
        TokenRequest tokenRequest = new TokenRequest(params, this.clientProperties.getClientId(), clientDetails.getScope(), this.clientProperties.getGrantType());
        Authentication userAuth = new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword());
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(tokenRequest.createOAuth2Request(clientDetails), userAuth);
        OAuth2AccessToken accessToken = this.defaultTokenServices.getAccessToken(oAuth2Authentication);
        if (accessToken != null) {
            return ResponseEntity.badRequest().body("登录失败，账户[" + loginInfo.getUsername() + "]已登录");
        }
        //</editor-fold>

        return this.tokenEndpoint.postAccessToken(authentication, params);
    }

    /**
     * 登出接口
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = HttpUtils.extractHeaderToken(request);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("遗失Authentication");
        }
        boolean result = this.defaultTokenServices.revokeToken(token);
        return result ? ResponseEntity.ok("登出成功") : ResponseEntity.badRequest().body("登出失败");
    }

    /**
     * 校验token
     */
    @GetMapping("/check-token")
    public ResponseEntity checkToken(HttpServletRequest request) {
        String token = HttpUtils.extractHeaderToken(request);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("遗失Authentication");
        }
        OAuth2AccessToken accessToken = this.defaultTokenServices.readAccessToken(token);
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Token无法识别");
        }
        if (accessToken.isExpired()) {
            this.defaultTokenServices.revokeToken(accessToken.getValue());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Token已过期");
        }
        OAuth2Authentication authentication = this.defaultTokenServices.loadAuthentication(accessToken.getValue());
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Token无效: " + token);
        }
        OAuth2AccessToken newAccessToken = this.tokenEnhancer.enhance(accessToken, authentication);
        return ResponseEntity.ok(newAccessToken);
    }
}
