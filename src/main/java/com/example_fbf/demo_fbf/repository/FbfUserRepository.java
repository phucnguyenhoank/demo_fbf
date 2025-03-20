package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.FbfUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FbfUserRepository extends JpaRepository<FbfUser, Long> {
}
