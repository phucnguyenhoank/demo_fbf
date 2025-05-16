package com.example_fbf.demo_fbf.service;


import com.example_fbf.demo_fbf.entity.Otp;

public interface OtpService {
    void deleteOtp(Otp otp);
    Otp getLatestOtpByEmail(String email);

    Otp saveOtp(Otp otp);
}
