package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.FbfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FbfUserRepository extends JpaRepository<FbfUser, Long> {
    Optional<FbfUser> findByUsername(String username);
    Optional<FbfUser> findByEmail(String email);
}
