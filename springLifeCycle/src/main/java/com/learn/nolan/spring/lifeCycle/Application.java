package com.learn.nolan.spring.lifeCycle;

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
        System.out.println(person);
        impl1();
    }

    public static void impl2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Object person = context.getBean("person");
        System.out.println(person);
    }

    public static void impl1(){
        ApplicationContext context = new AnnotationConfigApplicationContext("com.learn.nolan.spring.lifeCycle");
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
