package com.learn.dao;

import com.learn.BaseSpringBootTest;
import com.learn.model.User;
import helper.generator.UserGenerator;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseSpringBootTest {

    @Resource
    private UserDao userDao;

    @Test
    public void addUser() {
        // given
        User user = UserGenerator.generateRandom();
        System.out.println("待新增用户" + user);
        // when
        boolean success = userDao.addUser(user);
        // then
        assertTrue(success);
    }
}