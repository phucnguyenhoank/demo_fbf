package com.example_fbf.demo_fbf.auth;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.FbfRole;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.entity.Otp;
import com.example_fbf.demo_fbf.repository.FbfUserRepository;
import com.example_fbf.demo_fbf.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final FbfUserRepository fbfUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final OtpRepository otpRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<Otp> storedOtp = otpRepository.findTopByEmailOrderByExpirationTimeDesc(request.getEmail());

        if (storedOtp.isEmpty()) {
            throw new IllegalArgumentException("OTP not found");
        }

        Otp otpRecord = storedOtp.get();

        // Validate OTP expiration time
        if (otpRecord.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP has expired");
        }

        // Validate OTP match
        if (!otpRecord.getOtp().equals(request.getOtp())) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        otpRepository.delete(otpRecord);

        // Proceed with user registration
        var fbfUser = FbfUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .fbfRole(FbfRole.FBF_USER)
                .build();
        fbfUserRepository.save(fbfUser);

        var jwtToken = jwtService.generateToken(fbfUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var fbfUser = fbfUserRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(fbfUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
