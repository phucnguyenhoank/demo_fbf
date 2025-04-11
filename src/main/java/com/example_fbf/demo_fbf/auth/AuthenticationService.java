package com.example_fbf.demo_fbf.auth;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.FbfRole;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.entity.Otp;
import com.example_fbf.demo_fbf.repository.FbfUserRepository;
import com.example_fbf.demo_fbf.repository.OtpRepository;
import com.example_fbf.demo_fbf.service.CartService;
import com.example_fbf.demo_fbf.service.FbfUserService;
import com.example_fbf.demo_fbf.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final FbfUserService fbfUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;

    public AuthenticationResponse register(RegisterRequest request) {
        Otp otpRecord = otpService.getLatestOtpByEmail(request.getEmail());

        // Validate OTP expiration time
        if (otpRecord.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP has expired");
        }

        // Validate OTP match
        if (!otpRecord.getOtp().equals(request.getOtp())) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        otpService.deleteOtp(otpRecord);

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
        // create new cart and save new user
        fbfUserService.registerFbfUser(fbfUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put("cartId", fbfUser.getCart().getId());
        var jwtToken = jwtService.generateToken(claims, fbfUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var fbfUser = fbfUserService.findByUsername(request.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("cartId", fbfUser.getCart().getId());
        var jwtToken = jwtService.generateToken(claims, fbfUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
