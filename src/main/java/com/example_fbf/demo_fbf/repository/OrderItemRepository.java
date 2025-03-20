package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
