package com.github.xingshuangs.s7.demo.config;


import com.github.xingshuangs.iot.protocol.s7.enums.EPlcType;
import com.github.xingshuangs.iot.protocol.s7.service.S7PLC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 */
@Configuration
public class S7Config {

    @Bean
    public S7PLC s7PLC() {
        return new S7PLC(EPlcType.S1200, "127.0.0.1");
    }
}
