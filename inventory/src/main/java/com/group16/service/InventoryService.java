package com.group16.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.Dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @KafkaListener(topics = "userEvents", groupId = "inventory-group")
    public void listenUserRegistered(String message) {
        try {
            User userDto = objectMapper.readValue(message, User.class);
            logger.info("Received user registration event in Inventory Service: {}", userDto.getPhoneNumber());
            System.out.println("Received user registration event in Inventory Service: " + userDto.getPhoneNumber());
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            System.err.println("Error processing message: " + message + " Error: " + e.getMessage());
        }
    }
}

