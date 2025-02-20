package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "food_size_id", nullable = false)
    private FoodSize foodSize;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
