package com.sntgzrr.controllers;

import com.sntgzrr.models.*;
import com.sntgzrr.services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Transaction>> getTransactionByAccountUserId(@PathVariable Long userId){
        List<Transaction> transactions = this.accountService.getTransactionsByAccountUserId(userId);
        if (!transactions.isEmpty()){
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{userId}/cards")
    public ResponseEntity<List<Card>> getCardsByAccountUserId(@PathVariable Long userId){
        List<Card> cards = this.accountService.getCardsByAccountUserId(userId);
        if (!cards.isEmpty()){
            return new ResponseEntity<>(cards, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{userId}/cards/{cardId}")
    public ResponseEntity<Card> getCardByAccountUserIdAndId(@PathVariable Long userId, @PathVariable Long cardId){
        return this.accountService.getCardByAccountUserIdAndId(userId, cardId)
                .map(card -> new ResponseEntity<>(card, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{userId}/cards/{cardId}")
    public void deleteCardByAccountUserIdAndId(@PathVariable Long userId, @PathVariable Long cardId){
        this.accountService.deleteCardByAccountUserIdAndId(userId, cardId);
    }
    @GetMapping("/{userId}/activity")
    public ResponseEntity<List<Activity>> getActivitiesByAccountUserId(@PathVariable Long userId){
        List<Activity> activities = this.accountService.getActivitiesByAccountUserId(userId);
        if (!activities.isEmpty()){
            return new ResponseEntity<>(activities, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{userId}/activity/{transferId}")
    public ResponseEntity<Activity> getActivityByAccountUserIdAndTransferId(@PathVariable Long userId, @PathVariable Long transferId){
        return this.accountService.getActivityByAccountUserIdAndTransferId(userId, transferId)
                .map(activity -> new ResponseEntity<>(activity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/{cardId}/transferences")
    public Transfer saveTransfer(@RequestBody Transfer transfer, @PathVariable Long cardId){
        return this.accountService.saveTransfer(transfer, cardId);
    }
}
