package com.example.AccountMicroService.Repositories;

import com.example.AccountMicroService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


}
