package com.example_fbf.demo_fbf.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "food_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size; // "S", "M", "L", "XL"
    private Double price;
    private Integer stock;
    private Double discountPercentage; // e.g., 10.0 for 10%, null if no discount

    @ManyToOne
    @JoinColumn(name = "food_id")
    @JsonBackReference
    private Food food;
}

