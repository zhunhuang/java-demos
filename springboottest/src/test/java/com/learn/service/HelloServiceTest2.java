package com.learn.service;

import com.learn.BaseSpringBootTest;
import helper.generator.UserGenerator;
import helper.util.SpringBeanMockUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HelloServiceTest2 extends BaseSpringBootTest {

    @Resource
    @InjectMocks
    HelloService helloService;

    @Mock
    DateService dateService;

    @Resource
    UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void restore(){
        SpringBeanMockUtil.restoreSpringBeans(helloService);
    }

    @Test
    public void sayHello2() {

        // given
        userService.addUser(UserGenerator.generate(99,"nolan",20));
        when(dateService.currentDate()).thenReturn("星期三");

        // when
        String sayHello = helloService.sayHello(99);

        // then
        assertEquals("hello,nolan,today is 星期三", sayHello);
    }
}