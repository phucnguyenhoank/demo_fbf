package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.OrderItemDetailDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import com.example_fbf.demo_fbf.repository.OrderItemRepository;
import com.example_fbf.demo_fbf.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDetailDto> getAllOrderItemByOrderId(Long id) {
        return orderItemRepository.findDetailsByOrderId(id);
    }
}
