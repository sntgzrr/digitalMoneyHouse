package com.sntgzrr.controllers;

import com.sntgzrr.models.Account;
import com.sntgzrr.models.Transaction;
import com.sntgzrr.services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private final AccountServiceImpl accountService;
    @GetMapping("/{userId}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable Long userId){
        return accountService.getAccountByUserId(userId)
                .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{userId}/transactions")
    public ResponseEntity<Transaction> getTransactionByAccountUserId(@PathVariable Long userId){
        return accountService.getTransactionByAccountUserId(userId)
                .map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
