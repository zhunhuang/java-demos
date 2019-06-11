package com.learn.nolan.spring.lifeCycle.demo1;

import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author zhun.huang 2019-03-19
 */
@Component
public class Person {

    private String name;

    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{\"Person\":{"
                + "\"name\":\"" + name + "\""
                + ", \"age\":\"" + age + "\""
                + "}}";
    }
}
