package com.example.polyfood.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class SendMailConfig {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toMail,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sangpm01@gmail.com");
        message.setTo(toMail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail gửi thành công ...");
    }
}
