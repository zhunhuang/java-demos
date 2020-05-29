package com.learn.service;

import com.learn.BaseSpringBootTest;
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

public class HelloServiceTest extends BaseSpringBootTest {

    @Resource
    @InjectMocks
    HelloService helloService;

    @Mock
    UserService mockUserService;

    @Mock
    DateService mockDateService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void restore() {
        SpringBeanMockUtil.restoreSpringBeans(helloService);
    }

    @Test
    public void sayHello() {
        // given
        when(mockUserService.getUserName(1)).thenReturn("AAA");
        when(mockDateService.currentDate()).thenReturn("星期三");

        // when
        String sayHello = helloService.sayHello(1);
        // then
        assertEquals("hello,AAA,today is 星期三", sayHello);
    }
}