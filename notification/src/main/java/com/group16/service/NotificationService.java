package com.group16.service;

public interface NotificationService {
    void sendEmail(String receiver, String subject, String message);
}
