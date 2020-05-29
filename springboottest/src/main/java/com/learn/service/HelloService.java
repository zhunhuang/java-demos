package com.learn.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HelloService {

    @Resource
    private UserService userService;

    @Resource
    private DateService dateService;

    public String sayHello(int id) {
        String userName = userService.getUserName(id);
        String currentDate = dateService.currentDate();
        return "hello," + userName + ",today is " + currentDate;
    }
}
