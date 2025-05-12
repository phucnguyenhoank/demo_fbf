package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.OrderItemDetailDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import com.example_fbf.demo_fbf.service.impl.OrderItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-item")
public class OrderItemController {
    @Autowired
    private OrderItemServiceImpl orderItemService;

    @GetMapping("/get")
    public ApiResponse<List<OrderItemDetailDto>> getOrderItemByOrderId(@RequestParam(defaultValue = "1") Long id) {
        List<OrderItemDetailDto> orderItemDetailDtos = orderItemService.getAllOrderItemByOrderId(id);
        ApiResponse<List<OrderItemDetailDto>> response = new ApiResponse<>();
        response.setData(orderItemDetailDtos);
        response.setMessage("Request successful");
        return response;
    }
}
