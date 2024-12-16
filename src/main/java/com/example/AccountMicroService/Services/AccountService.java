package com.example.AccountMicroService.Services;

import com.example.AccountMicroService.Models.Account;
import com.example.AccountMicroService.Models.User;
import com.example.AccountMicroService.Repositories.AccountRepository;
import com.example.AccountMicroService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;



    public List<Account> getAccountsByUserId(Integer userId) {
        // Correct method call
        return accountRepository.findByUser_UserId(userId);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account addAccount(Account account) {

        User user = new User();
        user = userRepository.save(user);
        // Associate the newly created User with the Account
        account.setUser(user);
        // Save the Account with the associated User
        return accountRepository.save(account);
    }


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    public Account updateBalance(Long accountId, BigDecimal newBalance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }


    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
