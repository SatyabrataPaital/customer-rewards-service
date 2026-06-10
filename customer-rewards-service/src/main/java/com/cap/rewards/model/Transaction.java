package com.cap.rewards.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public record Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        String customerName,
        LocalDate date,
        double amount,

        @ManyToOne
        @JoinColumn(name = "customer_id")
        Customer customer
) {}
