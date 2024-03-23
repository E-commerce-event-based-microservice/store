package com.group16.service.implementation;

import com.group16.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImplementation implements NotificationService {
    private final JavaMailSender emailSender;

    public NotificationServiceImplementation(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(String receiver, String subject, String message) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("dev16ops2024@gmail.com");
        simpleMail.setTo("dev16ops2024@gmail.com");
        simpleMail.setSubject(subject);
        simpleMail.setText(message);

        this.emailSender.send(simpleMail);
    }


}