package com.test.javadev.services;

import com.test.javadev.entities.UserEntity;
import com.test.javadev.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public UserEntity save(UserEntity userEntity)
    {
        return userRepository.save(userEntity);
    }
}
