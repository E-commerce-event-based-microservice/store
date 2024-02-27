package com.group16.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "userEvents";

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(User user) {
        try {
            String userAsString = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(TOPIC, userAsString);
        } catch (JsonProcessingException e) {
            // Handle the exception properly - perhaps log it or throw a custom exception
            e.printStackTrace();
        }
    }
}