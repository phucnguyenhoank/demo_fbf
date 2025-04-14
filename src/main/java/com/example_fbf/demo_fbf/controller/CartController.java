package com.example_fbf.demo_fbf.controller;
import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example_fbf.demo_fbf.dto.CartDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final JwtService jwtService;
    private final CartService cartService;

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

    @GetMapping("/search/by-id")
    public ResponseEntity<CartDto> getCartById(@RequestParam(defaultValue = "") Long id){
        return cartService.findCartById(id).map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
