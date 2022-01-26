package com.github.xingshuangs.spring.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xingshuang
 */
@Data
@ConfigurationProperties(value = CustomProperties.PREFIX)
public class CustomProperties {

    public static final String PREFIX = "custom";

    private String username = "1111";

    private String password= "1111";
}
