package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Your OTP Code for FastBreakfast Registration");
        message.setText("Your OTP code is: " + otp);
        emailSender.send(message);
    }

    @Override
    @Async
    public void sendOtpAsync(String toEmail, String otp) {
        sendOtp(toEmail, otp);
    }
}

