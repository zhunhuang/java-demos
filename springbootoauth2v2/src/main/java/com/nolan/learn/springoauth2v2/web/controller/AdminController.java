package com.nolan.learn.springoauth2v2.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author zhun.huang 2019-03-25
 */
@RestController()
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "超级管理员才能看到的私密信息";
    }
}
