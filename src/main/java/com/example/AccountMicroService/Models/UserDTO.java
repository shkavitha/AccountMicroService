package com.example.AccountMicroService.Models;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
}
