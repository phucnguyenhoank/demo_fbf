package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
