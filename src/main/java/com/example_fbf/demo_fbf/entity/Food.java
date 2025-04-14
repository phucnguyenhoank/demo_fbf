package com.example_fbf.demo_fbf.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private String name;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
//    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "food")
//    @JsonManagedReference
    private List<FoodSize> sizes;
}
