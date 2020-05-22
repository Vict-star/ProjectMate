package com.example.projectmate.service;

import com.example.projectmate.domain.User;

import java.util.List;


public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    User findByOpenId(User user);//通过查找openID判断用户是否已存在，没有则addUser
}
