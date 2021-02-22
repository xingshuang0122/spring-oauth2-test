package com.github.xingshuangs.common.utils;

import com.google.common.net.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author ShuangPC
 * @date 2020/5/29
 */

public class HttpUtils {

    private static final String BEARER_TYPE = "Bearer";

    private HttpUtils() {

    }

    /**
     * 提取http请求的头部的Token
     *
     * @param request http请求
     * @return token
     */
    public static String extractHeaderToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(HttpHeaders.AUTHORIZATION);
        // typically there is only one (most servers enforce that)
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(BEARER_TYPE.length()).trim();
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        return null;
    }
}
