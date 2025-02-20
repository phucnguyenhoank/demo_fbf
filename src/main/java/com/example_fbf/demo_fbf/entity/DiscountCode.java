package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "discount_code")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Double discountPercentage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    @Column(nullable = false)
    private Boolean expired;

    @ManyToOne
    @JoinColumn(name = "order_item_id", nullable = true)
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "fbf_order_id", nullable = true)
    private FbfOrder order;
}

