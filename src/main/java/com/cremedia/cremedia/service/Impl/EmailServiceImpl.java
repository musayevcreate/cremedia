package com.cremedia.cremedia.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    public void sendMail(String email, String token) {
        log.info("mail sender started:");
        String subject = "Password Recovery";
        String message = String.format(
                "To reset your password, click the following link:\n\n" +
                        "http://localhost:8080/api/users/password-reset?token=%s\n\n" + //FIXME SWAGGER ADD , USE ENUM OR STATIC
                        "If you did not request a password reset, please ignore this email.",
                token
        );

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
        log.info("mail sender stopped");
    }
}