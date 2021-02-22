package com.github.xingshuangs.common.config;

import com.github.xingshuangs.common.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author ShuangPC
 * @date 2019/2/20
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 数据库数据重复异常
     *
     * @param e 异常
     * @return 数据重复的返回结果
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body("数据唯一键重复, " + e.getCause().getMessage());
    }

    /**
     * 自定义异常的返回结果
     *
     * @param e 异常
     * @return 自定义的返回结果
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity customException(CustomException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    /**
     * 运行时的异常
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity customException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    /**
     * 异常
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    /**
     * 方法输入参数校验异常，对应于对象
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String error = "参数输入错误：" + e.getBindingResult().getFieldErrors().stream()
                .map(x -> "[" + x.getField() + "]" + x.getDefaultMessage())
                .collect(Collectors.joining("，"));
        log.error(error, e);
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 方法输入参数校验异常，@Validated抛出的另一种异常，对应于@RequestParams和@PathVariable
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity constraintViolationException(ConstraintViolationException e) {
        String error = "参数输入错误：" + e.getConstraintViolations().stream()
                .map(x -> "[" + x.getPropertyPath() + "]" + x.getMessage())
                .collect(Collectors.joining("，"));
        log.error(error, e);
        return ResponseEntity.badRequest().body(error);
    }
}
