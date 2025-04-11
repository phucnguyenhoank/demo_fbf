package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{userId}/id")
    public ResponseEntity<Long> getCartIdByUserId(@PathVariable Long userId) {
        Long cartId = cartService.getCartIdByUserId(userId);
        return ResponseEntity.ok(cartId);
    }
}
