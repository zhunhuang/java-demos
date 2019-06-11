package com.nolan.learn.springbootsecurityjwt2.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String user() {
        return "登录才能看到的私密信息";
    }
}
