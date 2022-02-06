package com.github.xingshuangs.spring.configuration;


import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xingshuang
 */
@Slf4j
public class TestInit {

    @PostConstruct
    private void init() {
      log.info("初始化测试");
    }

    @PreDestroy
    private void destroy() {
        log.info("结束前测试");
    }
}
