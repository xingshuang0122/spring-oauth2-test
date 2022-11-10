package com.github.xingshuangs.s7.demo.controller;


import com.github.xingshuangs.iot.protocol.s7.serializer.S7Serializer;
import com.github.xingshuangs.s7.demo.model.DemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingshuang
 */
@RestController
@RequestMapping("/serializer")
public class S7SerializerComController {

    @Autowired
    private S7Serializer s7Serializer;

    @GetMapping("/bean")
    public ResponseEntity<DemoBean> demoBean() {
        DemoBean bean = this.s7Serializer.read(DemoBean.class);
        return ResponseEntity.ok(bean);
    }
}
