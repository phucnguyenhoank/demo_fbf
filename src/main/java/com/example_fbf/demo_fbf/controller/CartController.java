package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final JwtService jwtService;

    @GetMapping("/id")
    public ResponseEntity<ApiResponse<Long>> getCartIdFromToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);
        Long cartId = jwtService.getCartIdFromToken(token);


        ApiResponse<Long> response = new ApiResponse<>(
                true,
                "Cart ID fetched successfully for user: " + username + "has id: " + fbfUserId,
                cartId
        );

        return ResponseEntity.ok(response);
    }

}
