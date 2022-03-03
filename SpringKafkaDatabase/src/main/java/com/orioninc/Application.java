package com.orioninc;

import com.orioninc.properties.DBProperties;
import com.orioninc.properties.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.Timestamp;

@SpringBootApplication
@EnableConfigurationProperties({KafkaProperties.class, DBProperties.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
