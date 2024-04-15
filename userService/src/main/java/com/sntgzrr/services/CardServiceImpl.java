package com.sntgzrr.services;

import com.sntgzrr.models.Card;
import com.sntgzrr.repositories.ICardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {
    @Autowired
    private final ICardRepository iCardRepository;
    public Card saveCard(Card card){
        return this.iCardRepository.save(card);
    }
    public List<Card> readCards(){
        return this.iCardRepository.findAll();
    }
    public void deleteCardById(Long id){
        this.iCardRepository.deleteById(id);
    }
}
