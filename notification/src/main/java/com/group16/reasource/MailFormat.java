package com.group16.reasource;

public class MailFormat {
    private String receiver;
    private String subject;
    private String message;

    public MailFormat(){}

    public MailFormat(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

