package com.example_fbf.demo_fbf.dto;

import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.entity.FoodSize;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetailDto {
    private Long id;
    private String size;
    private Double discountedPrice;
    private Double discountPercentage;
    private Integer quantity;
    private String foodName;
    private String imageUrl;
    private LocalDateTime createdAt;
}
