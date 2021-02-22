/*
  Copyright (C), 2009-2020, 江苏汇博机器人技术股份有限公司
  FileName: InfluxDB2Example
  Author:   ShuangPC
  Date:     2020/5/19
  Description:
  History:
  <author>         <time>          <version>          <desc>
  作者姓名         修改时间           版本号             描述
 */

package com.github.xingshuangs.influxdb.demo.utils;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

/**
 * @author ShuangPC
 * @date 2020/5/19
 */

public class InfluxDB2Example {
    private static char[] token = "my-token".toCharArray();
    private static String org = "my-org";
    private static String bucket = "my-bucket";

    public static void main(final String[] args) {

        String database = "test";
        String retentionPolicy = "autogen";

        InfluxDBClient client = InfluxDBClientFactory.createV1("http://localhost:8086",
                "xingshuang",
                "123456".toCharArray(),
                database,
                retentionPolicy);

        System.out.println("*** Write Points ***");

        try (WriteApi writeApi = client.getWriteApi()) {

            Point point = Point.measurement("cpu")
                    .addTag("host", "serverA")
                    .addTag("region", "us_west")
                    .addField("value", 22.43);

            System.out.println(point.toLineProtocol());

            writeApi.writePoint(point);
        }

        System.out.println("*** Query Points ***");
        String query = String.format("from(bucket: \"%s/%s\") |> range(start: -1d) |> limit(n:100)", database, retentionPolicy);

        List<FluxTable> tables = client.getQueryApi().query(query);
        tables.get(0).getRecords()
                .forEach(record -> System.out.println(String.format("%s %s: %s %s",
                        Date.from(record.getTime()), record.getMeasurement(), record.getField(), record.getValue())));

        client.close();
    }

}
