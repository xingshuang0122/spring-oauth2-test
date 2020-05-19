package com.huibo.influxdb.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringInfluxdbDemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(System.nanoTime());
    }

    @Test
    public void test1(){
//        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8088", token, org, bucket);
    }
}
