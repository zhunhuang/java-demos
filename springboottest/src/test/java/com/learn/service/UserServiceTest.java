package com.learn.service;

import com.learn.BaseSpringBootTest;
import com.learn.dao.UserDao;
import helper.generator.UserGenerator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends BaseSpringBootTest {

    @Resource
    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @Test
    public void addUser_success() {
        // given
        when(userDao.addUser(any())).thenReturn(true);
        when(userDao.queryById(anyInt())).thenReturn(null);

        // when
        boolean success = userService.addUser(UserGenerator.generateRandom());

        // then
        assertTrue(success);
    }
}