package com.example.AccountMicroService.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Account> accounts;
}
