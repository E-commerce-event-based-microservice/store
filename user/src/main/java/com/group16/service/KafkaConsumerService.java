package com.group16.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "userEvents", groupId = "group_id")
    public void listen(Object message) {
        System.out.println("Received Message: " + message.toString());
    }
}
