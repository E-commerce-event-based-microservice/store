package com.group16.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
//    @Bean
//    public NewTopic orderTopic() {
//        return TopicBuilder.name("order").build();
//    }

    @Bean
    public NewTopic orderCreateTopic() {
        return TopicBuilder.name("orderCreate").build();
    }

    @Bean
    public NewTopic orderCreateSuccessTopic() {
        return TopicBuilder.name("orderCreateSuccess").build();
    }
}
