package com.sntgzrr.services;

import com.sntgzrr.models.Account;
import com.sntgzrr.models.User;
import com.sntgzrr.repositories.IAccountRepository;
import com.sntgzrr.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    private final IUserRepository iUserRepository;
    @Autowired
    private final IAccountRepository iAccountRepository;
    public User saveUser(User user){
        User savedUser = iUserRepository.save(user);

        Account account = new Account();
        account.setCvu(savedUser.getCvu());
        account.setUser(savedUser);
        iAccountRepository.save(account);

        return savedUser;
    }
    public Optional<User> getUserById(Long userId){
        return iUserRepository.getUserById(userId);
    }
}
