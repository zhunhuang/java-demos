package com.spring.learn.dao;

import com.spring.learn.model.User;

public interface UserDao {

    User queryById(int id);

    boolean addUser(User user);
}
