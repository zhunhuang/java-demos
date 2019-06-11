package com.learn.nolan.spring.lifeCycle.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description:
 *
 * @author zhun.huang 2019-03-19
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Object person = context.getBean("person");
        Cat cat = context.getBean(Cat.class);
        System.out.println(person);
        System.out.println(cat);
        System.out.println("运行到这表示person和cat两个bean都被注册成功");
        Object cat2 = context.getBean("cat");
        System.out.println(cat2);
        System.out.println("运行到这表示根据名字获取cat也能成功");
    }
}
