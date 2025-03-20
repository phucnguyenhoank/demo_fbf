package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
