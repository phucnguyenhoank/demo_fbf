package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private Long foodSizeId;
    private Long categoryId;
}