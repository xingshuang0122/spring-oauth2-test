package com.github.xingshuangs.s7.demo.controller;


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
@RequestMapping("/normal")
public class S7NormalComController {
    Random random = new Random();

    @Autowired
    private S7PLC s7PLC;

    @GetMapping("/uint16")
    public ResponseEntity<Integer> uint16() {
        this.s7PLC.writeUInt16("DB1.0", random.nextInt(255));
        int res = this.s7PLC.readUInt16("DB1.0");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/boolean")
    public ResponseEntity booleanTest() {
        this.s7PLC.writeBoolean("DB1.0.0", random.nextInt(255) % 2 == 0);
        boolean res = this.s7PLC.readBoolean("DB1.0.0");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/float32")
    public ResponseEntity float32Test() {
        this.s7PLC.writeFloat32("DB1.0", random.nextFloat());
        float res = this.s7PLC.readFloat32("DB1.0");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/string")
    public ResponseEntity stringTest() {
        this.s7PLC.writeString("DB1.0", String.valueOf(random.nextInt(20)));
        String res = this.s7PLC.readString("DB1.0");
        return ResponseEntity.ok(res);
    }
}
