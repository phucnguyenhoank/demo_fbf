package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findTopByEmailOrderByExpirationTimeDesc(String email);
}
