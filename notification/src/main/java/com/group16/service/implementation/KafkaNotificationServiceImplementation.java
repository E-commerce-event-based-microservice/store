package com.group16.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.Dto.Order;
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
<<<<<<< Updated upstream
   // @KafkaListener(topics = "userEvents", groupId = "inventory-group")
=======
    @KafkaListener(topics = "orderCreate", groupId = "group16.order")
>>>>>>> Stashed changes
    @Override
    public void sendMessage(String message) {
        try {
            Order orderDto = objectMapper.readValue(message, Order.class);
            logger.info("Received user order create event in Notification Service: {}", orderDto.getOrderId());
            System.out.println("Received order create event in Notification Service: " + orderDto.getOrderId());

            SimpleMailMessage simpleMail = new SimpleMailMessage();
            simpleMail.setFrom("dev16ops2024@gmail.com");
            simpleMail.setTo("dev16ops2024@gmail.com");
            simpleMail.setSubject("TEST");
            simpleMail.setText("Your order id is " + orderDto.getOrderId());

            this.emailSender.send(simpleMail);

        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            System.err.println("Error processing message: " + message + " Error: " + e.getMessage());
        }
    }
}
