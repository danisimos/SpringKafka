package com.orioninc.config;

import com.orioninc.exceptions.FailedIntervalDeserializationProvider;
import com.orioninc.exceptions.FailedProcessedIntervalSubscriptionsDeserializationProvider;
import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.properties.DBProperties;
import com.orioninc.properties.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.orioninc")
@EnableKafka
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Autowired
    KafkaProperties kafkaProperties;

    @Autowired
    DBProperties dbProperties;

    @Bean
    public ConsumerFactory<Interval, ProcessedIntervalSubscriptions> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());

        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.orioninc.models.ProcessedIntervalSubscriptions");
        config.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.orioninc.models.Interval");

        config.put(ErrorHandlingDeserializer.VALUE_FUNCTION, FailedProcessedIntervalSubscriptionsDeserializationProvider.class);
        config.put(ErrorHandlingDeserializer.KEY_FUNCTION, FailedIntervalDeserializationProvider.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Interval, ProcessedIntervalSubscriptions> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Interval, ProcessedIntervalSubscriptions> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(dbProperties.getDriver());
        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setUsername(dbProperties.getUser());
        dataSource.setPassword(dbProperties.getPassword());

        return dataSource;
    }
}
