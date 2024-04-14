package com.sntgzrr.services;

import com.sntgzrr.models.Account;
import com.sntgzrr.models.Transaction;
import com.sntgzrr.repositories.IAccountRepository;
import com.sntgzrr.repositories.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {
    @Autowired
    private final IAccountRepository iAccountRepository;
    @Autowired
    private final ITransactionRepository iTransactionRepository;
    public Account createAccount (Account account){
        return this.iAccountRepository.save(account);
    }
    public Optional<Account> getAccountByUserId(Long userId){
        return iAccountRepository.getAccountByUserId(userId);
    }
    public Optional<Transaction> getTransactionByAccountUserId(Long userId){
        return iTransactionRepository.getTransactionByAccountUserId(userId);
    }
}
