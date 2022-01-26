package com.github.xingshuangs.starter.demo.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xingshuang
 */
@Data
@ConfigurationProperties(value = DeviceProperties.PREFIX)
public class DeviceProperties {

    public static final String PREFIX = "device";

    private String name = "device1";

    private String nc= "aad";

    private Boolean open = false;
}
