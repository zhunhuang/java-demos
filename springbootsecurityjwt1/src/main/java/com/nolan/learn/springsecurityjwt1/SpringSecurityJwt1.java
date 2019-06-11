package com.nolan.learn.springsecurityjwt1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@SpringBootApplication
@EnableConfigurationProperties()
public class SpringSecurityJwt1 {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwt1.class, args);
    }
}
