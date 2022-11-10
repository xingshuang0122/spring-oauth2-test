package com.github.xingshuangs.s7.demo.model;


import com.github.xingshuangs.iot.protocol.common.enums.EDataType;
import com.github.xingshuangs.iot.protocol.s7.serializer.S7Variable;
import lombok.Data;

/**
 * 测试对象
 *
 * @author xingshuang
 */
@Data
public class DemoBean {

    @S7Variable(address = "DB1.0.1", type = EDataType.BOOL)
    private boolean bitData;

    @S7Variable(address = "DB1.4", type = EDataType.UINT16)
    private int uint16Data;

    @S7Variable(address = "DB1.6", type = EDataType.INT16)
    private short int16Data;

    @S7Variable(address = "DB1.8", type = EDataType.UINT32)
    private long uint32Data;

    @S7Variable(address = "DB1.12", type = EDataType.INT32)
    private int int32Data;

    @S7Variable(address = "DB1.16", type = EDataType.FLOAT32)
    private float float32Data;

    @S7Variable(address = "DB1.20", type = EDataType.FLOAT64)
    private double float64Data;

    @S7Variable(address = "DB1.28", type = EDataType.BYTE, count = 3)
    private byte[] byteData;
}
