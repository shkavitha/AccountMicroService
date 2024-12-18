package com.example.AccountMicroService.Services;

import com.example.AccountMicroService.Models.Account;
import com.example.AccountMicroService.Models.UserDTO;
import com.example.AccountMicroService.Repositories.AccountRepository;
import com.example.AccountMicroService.Services.Interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8080/users/";

    @Override
    public Account addAccount(Account account) {
        // Validate userId via User Microservice
        String userUrl = USER_SERVICE_URL + account.getUserId();
        ResponseEntity<UserDTO> userResponse = restTemplate.getForEntity(userUrl, UserDTO.class);

        if (!userResponse.getStatusCode().is2xxSuccessful() || userResponse.getBody() == null) {
            throw new RuntimeException("Invalid User ID");
        }
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAccountsByUserId(Long id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    @Override
    public Account updateBalance(Long accountId, BigDecimal newBalance) {
        // Find the account by ID
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
        // Update the balance
        account.setBalance(newBalance);
        // Save and return the updated account
        return accountRepository.save(account);

    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
        accountRepository.deleteById(accountId);
    }


}
