package com.nolan.learn.springoauth2v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * description:
 * 启动类
 * @author zhun.huang 2019-03-25
 */
@SpringBootApplication
@EnableConfigurationProperties()
public class springbootoauth2v2Application {

    public static void main(String[] args) {
        SpringApplication.run(springbootoauth2v2Application.class,args);
    }
}
