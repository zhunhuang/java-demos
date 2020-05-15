package com.test.nolan.aspectj.service;

import com.test.nolan.aspectj.annotation.LogAnnotation;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/19
 */
public class HelloService {

    @LogAnnotation(log = true)
    public String sayHello() {
        return "hello," + "nolan";
    }
}
