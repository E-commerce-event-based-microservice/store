package com.group16.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.group16.service.KafkaNotificationService;
import org.springframework.mail.SimpleMailMessage;
import com.group16.Dto.OrderFormDTO;

@Service
public class KafkaNotificationServiceImplementation implements KafkaNotificationService {

    private final Logger logger = LoggerFactory.getLogger(KafkaNotificationServiceImplementation.class);
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final JavaMailSender emailSender;

    public KafkaNotificationServiceImplementation(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    @KafkaListener(topics = "orderCreateSuccess", groupId = "group16.order")
    @Override
    public void sendOrderConformation(String message) {
        try {
            System.out.println("Received order: " + message);
            OrderFormDTO orderFormDTO = objectMapper.readValue(message, OrderFormDTO.class);
            System.out.println("Received order: " + orderFormDTO.getEmail());
            SimpleMailMessage simpleMail = new SimpleMailMessage();
            simpleMail.setFrom("dev16ops2024@gmail.com");
            simpleMail.setTo(orderFormDTO.getEmail());
            simpleMail.setSubject("Order Created");
            simpleMail.setText("Your order has been created! Your order ID is " + orderFormDTO.getOrderId());

            this.emailSender.send(simpleMail);


        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            System.err.println("Error processing message: " + message + " Error: " + e.getMessage());
        }
    }
}
