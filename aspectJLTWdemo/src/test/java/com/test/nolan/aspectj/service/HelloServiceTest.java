package com.test.nolan.aspectj.service;

import org.junit.Test;

public class HelloServiceTest {

    @Test
    public void sayHello() {
        HelloService helloService = new HelloService();

        helloService.sayHello();
    }
}