package com.learn.service;

import com.learn.dao.UserDao;
import com.learn.exception.DuplicateDataException;
import com.learn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean addUser(User user) {
        User user1 = userDao.queryById(user.getId());
        if (user1 != null) {
            throw new DuplicateDataException("用户已存在");
        }
        return userDao.addUser(user);
    }

    public String getUserName(int id) {
        User user = userDao.queryById(id);
        if (user == null) {
            throw new RuntimeException("未找到用户");
        }
        return user.getName();
    }
}
