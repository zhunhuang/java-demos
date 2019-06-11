package com.nolan.learn.springbootsecurityjwt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * description:
 *
 * @author zhun.huang 2019-03-27
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Springbootsecurityjwt2 {

    public static void main(String[] args) {
        SpringApplication.run(Springbootsecurityjwt2.class,args);
    }

}
