package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.service.CartItemService;
import com.example_fbf.demo_fbf.service.impl.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/cart")
public class CartItemController {

    @Autowired
    private CartItemServiceImpl cartItemService;

    @GetMapping
    public List<CartItemDto> findCartItemByCartId(@RequestParam(defaultValue = "0") Long id){
        List<CartItemDto> cartItemList = cartItemService.findCartItemByCartId(id);
        return cartItemList;
    }


}
