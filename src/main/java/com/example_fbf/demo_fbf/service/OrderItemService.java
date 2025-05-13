package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.OrderItemDetailDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    List<OrderItemDetailDto> getAllOrderItemByOrderId(Long id);
}
