package com.learn.nolan.spring.lifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 *
 * @author zhun.huang 2019-03-19
 */
//@Configuration
//@ComponentScan
public class ApplicationConfig {

    @Bean("person")
    Person getPerson(){
        return new Person("nolan",26);
    }
}
