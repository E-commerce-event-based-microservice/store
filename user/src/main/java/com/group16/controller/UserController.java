package com.group16.controller;

import com.group16.model.User;
import com.group16.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/users")
    public void registerUser(@RequestBody User user) {
        kafkaProducerService.sendMessage(user);
        // Additional logic for user registration
    }
}
