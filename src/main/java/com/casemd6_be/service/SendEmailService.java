package com.casemd6_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    @Autowired
    JavaMailSender mailSender;

    public boolean sendMail(String toMail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("chivang1997a@gmail.com");
        message.setTo(toMail);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            return true;
        } catch (MailException mailException){
            System.out.println("Error"+mailException.getMessage());
            return false;
        }
    }
}
