package com.test.nolan.controller;

import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午4:09
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Controller
public class TestController {

    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();
        Thread.sleep(100);
        Date date2 = new Date();
        System.out.println(date1.compareTo(date2) > 0);
    }
}
