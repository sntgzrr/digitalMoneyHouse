package com.sntgzrr.repositories;

import com.sntgzrr.models.Account;
import com.sntgzrr.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    Optional<Account> getAccountByUserId(Long userId);
}
