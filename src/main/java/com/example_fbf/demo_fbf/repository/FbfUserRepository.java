package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.FbfUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FbfUserRepository extends JpaRepository<FbfUser, Long> {
    Optional<FbfUser> findByUsername(String username);
}
