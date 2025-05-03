package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.auth.*;
import com.example_fbf.demo_fbf.entity.Otp;
import com.example_fbf.demo_fbf.repository.OtpRepository;
import com.example_fbf.demo_fbf.service.EmailService;
import com.example_fbf.demo_fbf.service.OtpService;
import com.example_fbf.demo_fbf.util.OtpGenerator;
import com.example_fbf.demo_fbf.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final EmailService emailService;

    private final OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        // Xác nhận OTP
        // OTP hợp lệ thì gửi lại response hợp lệ
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<String>> sendOtp(@RequestParam String email) {
        String otp = OtpGenerator.generateOtp();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);

        Otp otpRecord = new Otp(email, otp, expirationTime);
        otpService.saveOtp(otpRecord);
        emailService.sendOtpAsync(email, otp);
        ApiResponse<String> response = new ApiResponse<>(true, "OTP sent to email.", "No addition");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<ApiResponse<String>> requestPasswordReset(@RequestParam String email) {
        ApiResponse<String> response = authenticationService.requestPasswordReset(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        ApiResponse<String> response = authenticationService.resetPassword(request);
        return ResponseEntity.ok(response);
    }

}
