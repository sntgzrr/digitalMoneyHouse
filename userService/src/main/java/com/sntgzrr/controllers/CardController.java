package com.sntgzrr.controllers;

import com.sntgzrr.models.Card;
import com.sntgzrr.services.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CardController {
    private final CardServiceImpl cardService;
    @GetMapping
    public List<Card> getCards(){
        return this.cardService.readCards();
    }
    @PostMapping
    public Card createCard (@RequestBody Card card){
        return this.cardService.saveCard(card);
    }
    @PutMapping
    public Card updateCard (@RequestBody Card card){
        return this.cardService.saveCard(card);
    }
    @DeleteMapping("/{id}")
    public void deleteCardById (@PathVariable Long id){
        this.cardService.deleteCardById(id);
    }
}
