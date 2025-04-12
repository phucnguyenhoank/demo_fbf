package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fbf_user_id")
    private FbfUser fbfUser;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}

