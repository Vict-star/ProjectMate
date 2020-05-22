package com.example.projectmate.service.Impl;

import com.example.projectmate.dao.UserDao;
import com.example.projectmate.domain.User;
import com.example.projectmate.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user){userDao.addUser(user);}
    @Override
    public void updateUser(User user){userDao.updateUser(user);}
    @Override
    public User findByOpenId(User user){
        PageHelper.startPage(1, 1);
        return userDao.findByOpenId(user);
    }
}
