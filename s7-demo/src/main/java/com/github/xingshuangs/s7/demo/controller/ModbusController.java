package com.github.xingshuangs.s7.demo.controller;


import com.github.xingshuangs.iot.protocol.modbus.service.ModbusTcp;
import com.github.xingshuangs.iot.protocol.s7.service.S7PLC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author xingshuang
 */
@RestController
@RequestMapping("/modbus")
public class ModbusController {
    Random random = new Random();

    @Autowired
    private ModbusTcp modbusTcp;

    @GetMapping("/uint16")
    public ResponseEntity<Integer> uint16() {
//        log.info("uint16");
        this.modbusTcp.writeUInt16(1, random.nextInt(255));
        int res = this.modbusTcp.readUInt16(1);
        return ResponseEntity.ok(res);
    }
}
