package com.github.xingshuangs.s7.demo.config;


import com.github.xingshuangs.iot.protocol.modbus.service.ModbusTcp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingshuang
 */
@Configuration
public class ModbusConfig {

    @Bean
    public ModbusTcp modbusTcp() {
        return new ModbusTcp(1);
    }
}
