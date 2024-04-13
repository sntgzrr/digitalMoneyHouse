package com.sntgzrr.services;

import com.sntgzrr.models.User;
import com.sntgzrr.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    private final IUserRepository iUserRepository;

    public User saveUser(User user){
        return iUserRepository.save(user);
    }
}
