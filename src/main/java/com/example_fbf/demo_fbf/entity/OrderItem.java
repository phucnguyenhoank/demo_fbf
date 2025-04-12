package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_size_id")
    private FoodSize foodSize;
    private Double discountedPrice;
    private Double discountPercentage; // Copied from CartItem
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "fbf_order_id")
    private FbfOrder fbfOrder;
}
