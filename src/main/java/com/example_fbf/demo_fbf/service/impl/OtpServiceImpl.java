package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.entity.Otp;
import com.example_fbf.demo_fbf.repository.OtpRepository;
import com.example_fbf.demo_fbf.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    @Override
    public void deleteOtp(Otp otp) {
        otpRepository.delete(otp);
    }

    @Override
    public Otp getLatestOtpByEmail(String email) {
        return otpRepository.findTopByEmailOrderByExpirationTimeDesc(email)
                .orElseThrow(() -> new IllegalArgumentException("No OTP found for email: " + email));
    }
}
