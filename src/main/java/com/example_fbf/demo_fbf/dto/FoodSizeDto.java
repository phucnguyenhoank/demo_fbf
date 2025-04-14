package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodSizeDto {
    private Long id;
    private String size;
    private Double price;
    private Integer stock;
    private Double discountPercentage;
    private Long foodId;
}
