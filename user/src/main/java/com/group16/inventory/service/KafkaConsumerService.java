package com.group16.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "userEvents", groupId = "inventory-group")
    public void listenUserRegistered(String message) {
        System.out.println("Received user registration event in Inventory Service: " + message);
    }
}
