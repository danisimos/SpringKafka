package com.orioninc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db")
@Data
public class DBProperties {
    private String url;
    private String user;
    private String password;
    private String driver;
}
