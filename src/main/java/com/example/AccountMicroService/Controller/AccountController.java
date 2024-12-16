package com.example.AccountMicroService.Controller;

import com.example.AccountMicroService.Models.Account;
import com.example.AccountMicroService.Services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/user/{userId}")
    public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @GetMapping("account/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PutMapping("account/{id}/balance")
    public ResponseEntity<Account> updateBalance(@PathVariable("id") Long accountId, @RequestParam BigDecimal balance) {
        Account updatedAccount = accountService.updateBalance(accountId, balance);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("account/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
