package com.learn.dao;

import com.learn.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface UserDao {

    User queryById(int id);

    boolean addUser(User user);
}
