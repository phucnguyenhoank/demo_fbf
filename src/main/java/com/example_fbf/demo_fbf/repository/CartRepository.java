package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByFbfUserId(Long userId);
}
