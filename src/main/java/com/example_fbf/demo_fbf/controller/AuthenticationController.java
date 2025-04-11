package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.auth.AuthenticationRequest;
import com.example_fbf.demo_fbf.auth.AuthenticationResponse;
import com.example_fbf.demo_fbf.auth.AuthenticationService;
import com.example_fbf.demo_fbf.auth.RegisterRequest;
import com.example_fbf.demo_fbf.entity.Otp;
import com.example_fbf.demo_fbf.repository.OtpRepository;
import com.example_fbf.demo_fbf.service.EmailService;
import com.example_fbf.demo_fbf.util.OtpGenerator;
import com.example_fbf.demo_fbf.wrapper.ApiResponse;
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

    private final OtpRepository otpRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        // Xác nhận OTP
        // OTP hợp lệ thì gửi lại response hợp lệ
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<String>> sendOtp(@RequestParam String email) {
        String otp = OtpGenerator.generateOtp();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5); // Thời gian hết hạn OTP

        // Lưu OTP vào cơ sở dữ liệu
        Otp otpRecord = new Otp(email, otp, expirationTime);
        otpRepository.save(otpRecord);

        // Gửi OTP tới email của người dùng
        emailService.sendOtp(email, otp);

        // Tạo phản hồi JSON
        ApiResponse<String> response = new ApiResponse<>(
                true,
                "OTP sent to email.",
                "No addition"
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
