package com.sntgzrr.services;

import com.sntgzrr.models.Account;
import com.sntgzrr.models.Activity;
import com.sntgzrr.models.Card;
import com.sntgzrr.models.Transaction;
import com.sntgzrr.repositories.IAccountRepository;
import com.sntgzrr.repositories.IActivityRepository;
import com.sntgzrr.repositories.ICardRepository;
import com.sntgzrr.repositories.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {
    @Autowired
    private final IAccountRepository iAccountRepository;
    @Autowired
    private final ITransactionRepository iTransactionRepository;
    @Autowired
    private final ICardRepository iCardRepository;
    @Autowired
    private  final IActivityRepository iActivityRepository;
    public Account createAccount (Account account){
        return this.iAccountRepository.save(account);
    }
    public Optional<Account> getAccountByUserId(Long userId){
        return iAccountRepository.getAccountByUserId(userId);
    }
    public List<Transaction> getTransactionsByAccountUserId(Long userId){
        return iTransactionRepository.findTransactionsByAccountUserId(userId);
    }
    public List<Card> getCardsByAccountUserId(Long userId){
        return this.iCardRepository.findCardsByAccountUserId(userId);
    }
    public Optional<Card> getCardByAccountUserIdAndId(Long userId, Long cardId){
        return this.iCardRepository.findCardByAccountUserIdAndId(userId, cardId);
    }
    public void deleteCardByAccountUserIdAndId(Long userId, Long cardId){
        this.iCardRepository.deleteCardByAccountUserIdAndId(userId, cardId);
    }
    public List<Activity> getActivitiesByAccountUserId(Long userId){
        return this.iActivityRepository.findActivitiesByAccountUserId(userId);
    }
    public Optional<Activity> getActivityByAccountUserIdAndTransferId(Long userId, Long transferId){
        return this.iActivityRepository.findActivityByAccountUserIdAndTransferId(userId, transferId);
    }
}
