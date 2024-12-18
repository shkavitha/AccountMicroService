package com.example.AccountMicroService.Services.Interfaces;

import com.example.AccountMicroService.Models.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account addAccount(Account account);
    List<Account> getAllAccounts();
    List<Account>  getAccountsByUserId(Long id);
    Account getAccount(Long id);
    Account updateBalance(Long accountId, BigDecimal newBalance);
    void deleteAccount(Long accountId);

}
