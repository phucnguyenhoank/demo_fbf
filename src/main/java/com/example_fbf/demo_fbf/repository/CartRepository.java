package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
