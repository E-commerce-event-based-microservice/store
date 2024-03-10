package com.group16.controller;

import com.group16.reasource.MailFormat;
import com.group16.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @CrossOrigin
    @PostMapping("/send_notification")
    public ResponseEntity sendMessage(@RequestBody MailFormat mailFormat){
        this.notificationService.sendEmail(mailFormat.getReceiver(), mailFormat.getSubject(), mailFormat.getMessage());
        return ResponseEntity.ok("Mail successfully sent");
    }
}
