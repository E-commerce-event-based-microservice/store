package com.group16.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @KafkaListener(topics = "userEvents", groupId = "inventory-group")
    public void handleUserRegistration(String message) {
        // Assuming message is a simple string. For complex objects, consider using a JSON parser.
        logger.info("Received user registration event: {}", message);
        // Here you can parse the message and perform inventory-related operations
        // For example, initialize inventory for the new user, etc.
    }
}



