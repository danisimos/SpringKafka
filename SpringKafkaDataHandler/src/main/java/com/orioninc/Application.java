package com.orioninc;

import com.orioninc.properties.ApplicationProperties;
import com.orioninc.properties.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({KafkaProperties.class, ApplicationProperties.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


}
