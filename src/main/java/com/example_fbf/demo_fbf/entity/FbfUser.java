package com.example_fbf.demo_fbf.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fbf_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FbfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String phoneNumber;
    private String address;
}

