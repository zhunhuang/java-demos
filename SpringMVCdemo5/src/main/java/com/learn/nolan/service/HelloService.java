package com.learn.nolan.service;

import com.learn.nolan.dao.UserDao;
import com.learn.nolan.model.UserDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.LongAdder;

/**
 * description:
 *
 * @author zhun.huang 2018-12-17
 */
@Service
public class HelloService {

    @Resource
    private UserDao userDao;

    public UserDO sayHello() {
        return userDao.queryById(1);
    }
}
