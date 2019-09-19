package com.huibo.influxdb.demo;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringInfluxdbDemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(System.nanoTime());
    }

    @Test
    public void test1(){
        InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "admin", "admin");
        String dbName = "aTimeSeries";
        influxDB.query(new Query("CREATE DATABASE " + dbName));
        influxDB.setDatabase(dbName);
        String rpName = "aRetentionPolicy";
        influxDB.query(new Query("CREATE RETENTION POLICY " + rpName + " ON " + dbName + " DURATION 30h REPLICATION 2 SHARD DURATION 30m DEFAULT"));
        influxDB.setRetentionPolicy(rpName);

        influxDB.enableBatch(BatchOptions.DEFAULTS);

        influxDB.write(Point.measurement("cpu")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("idle", 90L)
                .addField("user", 9L)
                .addField("system", 1L)
                .build());

        influxDB.write(Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("used", 80L)
                .addField("free", 1L)
                .build());

//        Query query = new Query("SELECT idle FROM cpu", dbName);
//        influxDB.query(query);
//        influxDB.query(new Query("DROP RETENTION POLICY " + rpName + " ON " + dbName));
//        influxDB.query(new Query("DROP DATABASE " + dbName));
        influxDB.close();
    }

}
