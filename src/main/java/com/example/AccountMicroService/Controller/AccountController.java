package com.example.AccountMicroService.Controller;

import com.example.AccountMicroService.Models.Account;
import com.example.AccountMicroService.Models.UserDTO;
import com.example.AccountMicroService.Repositories.AccountRepository;
import com.example.AccountMicroService.Services.AccountServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImp accountServiceImp;

    @GetMapping
    public ResponseEntity<List<Account>>GetAllAccounts(){
        return ResponseEntity.ok(accountServiceImp.getAllAccounts());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountServiceImp.addAccount(account));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getAllAccountsByUserId(@PathVariable Long userId) {
        List<Account> accounts = accountServiceImp.getAccountsByUserId(userId);
        if (accounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountServiceImp.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{accountId}/balance")
    public ResponseEntity<Account> updateBalance(
            @PathVariable Long accountId,
            @RequestBody Map<String, Double> requestBody) {

        // Extract the new balance from the request body
        BigDecimal newBalance = BigDecimal.valueOf(requestBody.getOrDefault("newBalance", 0.0));

        // Update the balance and return the updated account
        Account updatedAccount = accountServiceImp.updateBalance(accountId, newBalance);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountServiceImp.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully.");
    }

}
