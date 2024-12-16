package com.example.AccountMicroService.Models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;


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

}
