package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午4:14
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TestTask {

    @Test
    public void TestSchedule() {
        ApplicationContext ct = new ClassPathXmlApplicationContext("classpath:spring/root-context.xml");
        ct.getBean("sayHello");
        System.out.println("=============================start=========================");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
