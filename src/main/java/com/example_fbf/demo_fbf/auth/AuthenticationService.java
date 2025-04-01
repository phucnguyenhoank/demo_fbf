package com.example_fbf.demo_fbf.auth;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.entity.FbfRole;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.repository.FbfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final FbfUserRepository fbfUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
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
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var fbfUser = fbfUserRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(fbfUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
