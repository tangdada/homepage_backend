package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {
    public User getUserById(int userId);

    public User getUserByUserName(String userName);

    boolean addUser(User record);

}