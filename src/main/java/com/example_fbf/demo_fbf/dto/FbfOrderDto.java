package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbfOrderDto {
    private Long id;
    private Double discountedTotalPrice;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private Long fbfUserId;
    private DiscountCodeDto discountCode;
    private List<OrderItemDto> items;
    private String status;
}
