package com.example_fbf.demo_fbf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendOtp(String toEmail, String otp);
    void sendOtpAsync(String toEmail, String otp);
}
