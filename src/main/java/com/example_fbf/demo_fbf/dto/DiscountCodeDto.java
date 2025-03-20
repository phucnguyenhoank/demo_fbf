package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCodeDto {
    private Long id;
    private String code;
    private Double discountPercentage;
    private LocalDateTime expirationDate;
}
