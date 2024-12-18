package com.example.AccountMicroService.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String accountType; // e.g., "Checking", "Savings"

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    @Column(nullable = false, length = 10)
    private String currency="USD";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();


//    public Account(Long userId, String accountName) {
//        this.userId=userId;
//        this.accountName=accountName;
//    }
}
