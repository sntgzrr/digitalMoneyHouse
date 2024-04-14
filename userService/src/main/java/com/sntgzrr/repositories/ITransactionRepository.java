package com.sntgzrr.repositories;

import com.sntgzrr.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> getTransactionByAccountUserId(Long userId);
}
