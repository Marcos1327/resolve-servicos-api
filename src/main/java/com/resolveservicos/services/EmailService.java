package com.resolveservicos.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String title, String message) {

        SimpleMailMessage content = new SimpleMailMessage();

        content.setTo(to);
        content.setSubject(title);
        content.setText(message);
        javaMailSender.send(content);
    }

    public void sendEmailWithAttachment(String to, String title, String message, String pathToAttachment) throws MessagingException {
        MimeMessage content = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(content, true);

        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(message, true);
        helper.addAttachment("logo.png", new ClassPathResource(pathToAttachment));

        javaMailSender.send(content);


    }
}
