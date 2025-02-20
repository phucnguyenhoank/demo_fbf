package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false, unique = true)
    private String sizeName; // Small, Medium, Large, Extra Large
}

