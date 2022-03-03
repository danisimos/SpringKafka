package com.orioninc.config;

import com.orioninc.exceptions.FailedSubscriptionDeserializationProvider;
import com.orioninc.exceptions.FailedUserDeserializationProvider;
import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.properties.KafkaProperties;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.orioninc")
@EnableKafka
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Autowired
    KafkaProperties kafkaProperties;

    @Bean
    public NewTopic subscriptionsTopic() {
        return TopicBuilder.name(kafkaProperties.getTopics().getSubscriptionsTopic())
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic intervalsTopic() {
        return TopicBuilder.name(kafkaProperties.getTopics().getIntervalsTopic())
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic metricCountTopic() {
        return TopicBuilder.name(kafkaProperties.getTopics().getMetricCountTopic())
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());

        KafkaAdmin kafkaAdmin = new KafkaAdmin(configs);
        kafkaAdmin.createOrModifyTopics(subscriptionsTopic(), intervalsTopic(), metricCountTopic());

        return kafkaAdmin;
    }

    @Bean
    public ConsumerFactory<User, Subscription> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());

        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.orioninc.models.Subscription");
        config.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.orioninc.models.User");

        config.put(ErrorHandlingDeserializer.VALUE_FUNCTION, FailedUserDeserializationProvider.class);
        config.put(ErrorHandlingDeserializer.KEY_FUNCTION, FailedSubscriptionDeserializationProvider.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<User, Subscription> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<User, Subscription> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ProducerFactory<User, Subscription> producerFactorySubscription() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<User, Subscription> kafkaTemplateSubscription() {
        KafkaTemplate<User, Subscription> kafkaTemplate = new KafkaTemplate<>(producerFactorySubscription());
        kafkaTemplate.setDefaultTopic(kafkaProperties.getTopics().getSubscriptionsTopic());

        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<Interval, ProcessedIntervalSubscriptions> producerFactoryProcessedIntervalSubscriptions() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<Interval, ProcessedIntervalSubscriptions> kafkaTemplateProcessedIntervalSubscriptions() {
        KafkaTemplate<Interval, ProcessedIntervalSubscriptions> kafkaTemplate = new KafkaTemplate<>(producerFactoryProcessedIntervalSubscriptions());
        kafkaTemplate.setDefaultTopic(kafkaProperties.getTopics().getIntervalsTopic());

        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, Subscription> producerFactoryMetricCount() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Subscription> kafkaTemplateMetricCount() {
        KafkaTemplate<String, Subscription> kafkaTemplate = new KafkaTemplate<>(producerFactoryMetricCount());
        kafkaTemplate.setDefaultTopic(kafkaProperties.getTopics().getMetricCountTopic());

        return kafkaTemplate;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
