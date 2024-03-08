package com.group16.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.Dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.group16.service.KafkaNotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class KafkaNotificationServiceImplementation implements KafkaNotificationService {

    private final Logger logger = LoggerFactory.getLogger(KafkaNotificationServiceImplementation.class);
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final JavaMailSender emailSender;

    public KafkaNotificationServiceImplementation(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    @KafkaListener(topics = "userEvents", groupId = "inventory-group")
    @Override
    public void sendMessage(String message) {
        try {
            User userDto = objectMapper.readValue(message, User.class);
            logger.info("Received user registration event in Notification Service: {}", userDto.getPhoneNumber());
            System.out.println("Received user registration event in Notification Service: " + userDto.getPhoneNumber());

            SimpleMailMessage simpleMail = new SimpleMailMessage();
            simpleMail.setFrom("dev16ops2024@gmail.com");
            simpleMail.setTo(userDto.getEmail());
            simpleMail.setSubject("TEST");
            simpleMail.setText(message);

            this.emailSender.send(simpleMail);

        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            System.err.println("Error processing message: " + message + " Error: " + e.getMessage());
        }
    }
}
