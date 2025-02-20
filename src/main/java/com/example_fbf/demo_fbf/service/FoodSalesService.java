package com.example_fbf.demo_fbf.service;
import com.example_fbf.demo_fbf.dto.FoodSalesDto;
import com.example_fbf.demo_fbf.entity.OrderStatus;
import com.example_fbf.demo_fbf.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodSalesService {

    private final OrderItemRepository orderItemRepository;

    public List<FoodSalesDto> getTopSoldFoods() {
        // Lấy danh sách FoodSalesDto cho các sản phẩm bán được (đơn hàng có trạng thái PAID)
        return orderItemRepository.findTopSoldFoods(OrderStatus.PAID);
    }
}
