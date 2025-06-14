package com.example_fbf.demo_fbf.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private FoodSize foodSize;

    private Double discountedPrice;
    private Double discountPercentage; // Copied from CartItem
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "fbf_order_id")
    @JsonBackReference
    private FbfOrder fbfOrder;
}
