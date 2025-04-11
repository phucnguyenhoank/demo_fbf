package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime expirationTime;  // OTP expiration time

    public Otp(String email, String otp, LocalDateTime expirationTime) {
        this.email = email;
        this.otp = otp;
        this.expirationTime = expirationTime;
    }
}

