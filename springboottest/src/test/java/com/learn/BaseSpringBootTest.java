package com.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import helper.util.SpringUtils;

/**
 * 1. 仍然使用SpringJUnit4ClassRunner作为执行环境，支持 spring的注解。
 * 2. 使用SpringBootTest注解指定 boot上下文及其配置和指定web端口等。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootStrap.class)
public class BaseSpringBootTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.setApplicationContext(applicationContext);
    }

    @Test
    public void test() {
        System.out.println("hello test");
    }
}
