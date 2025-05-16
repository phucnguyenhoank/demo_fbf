package com.example_fbf.demo_fbf.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private Double discountedTotalPrice;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fbf_user_id")
    private FbfUser fbfUser;

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    @OneToMany(mappedBy = "fbfOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FbfOrderStatus status = FbfOrderStatus.PENDING;

}

