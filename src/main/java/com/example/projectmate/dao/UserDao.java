package com.example.projectmate.dao;

import com.example.projectmate.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    User findByOpenId(User user);//通过查找openID判断用户是否已存在，没有则addUser
}
