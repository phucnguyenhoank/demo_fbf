package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "fbf_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FbfOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status; // CREATED, PAID

    @ManyToOne
    @JoinColumn(name = "fbf_user_id", nullable = false)
    private FbfUser user;
}

