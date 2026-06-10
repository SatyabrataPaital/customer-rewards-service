package com.cap.rewards.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
public record Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        String name,
        String email,

        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
        List<Transaction> transactions
) {}

