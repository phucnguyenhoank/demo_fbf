package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/search/by-id")
    public ResponseEntity<CartDto> getCartById(@RequestParam(defaultValue = "") Long id){
        return cartService.findCartById(id).map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
