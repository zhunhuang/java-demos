package com.nolan.learn.springbootsecurity.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author zhun.huang 2019-03-22
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @RequestMapping()
    public String user() {
        return "登录才能看到的私密信息";
    }
}
