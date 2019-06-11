package com.learn.nolan.spring.lifeCycle.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 *
 * @author zhun.huang 2019-03-19
 */
//↓↓↓↓↓↓@ComponentScan表示扫描当前包(hello)及其子包下的所有类,注入到spring容器中
@ComponentScan
@Configuration
public class ApplicationConfig {

    @Bean
    Cat getCat(){
        return new Cat("lala",1000);
    }

}
