package com.ab.mfa.services;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        String ...templateModel);
}
