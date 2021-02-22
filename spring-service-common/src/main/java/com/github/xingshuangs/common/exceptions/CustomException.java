package com.github.xingshuangs.common.exceptions;


import org.springframework.http.HttpStatus;

/**
 * 自定义异常类型
 *
 * @author xingshuang
 * @date 2019/4/26
 */
public class CustomException extends RuntimeException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
