package com.sntgzrr.repositories;

import com.sntgzrr.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {
    List<Card> findCardsByAccountUserId(Long userId);
    Optional<Card> findCardByAccountUserIdAndId(Long userId, Long cardId);
    void deleteCardByAccountUserIdAndId(Long userId, Long cardId);
}
