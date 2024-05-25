package com.softuni.M01SDIntroLab.services;


import com.softuni.M01SDIntroLab.models.User;

public interface UserService {
    void register(String  username, int age);

    User findByUsername(String first);
}
