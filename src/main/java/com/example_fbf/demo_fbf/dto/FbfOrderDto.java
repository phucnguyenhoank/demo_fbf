package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbfOrderDto {
    private Long id;
    private Double totalPrice;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private Long userId;
    private DiscountCodeDto discountCode;
    private List<OrderItemDto> items;
}
