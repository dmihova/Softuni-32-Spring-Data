package com.softuni.M01SDIntroLab.services;

import com.softuni.M01SDIntroLab.models.Account;
import com.softuni.M01SDIntroLab.models.User;
import com.softuni.M01SDIntroLab.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, int age) {
        // Validate username and age
        if (username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed");
        }

        // check username is unique
        Optional<User> oldUser = this.userRepository.findByUsername(username);
        if (oldUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }//create default account
        Account account = new Account();

        // Save user
        User newUser = new User(username,age,account);
        account.setUser(newUser);
        this.userRepository.save(newUser);

    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }
}
