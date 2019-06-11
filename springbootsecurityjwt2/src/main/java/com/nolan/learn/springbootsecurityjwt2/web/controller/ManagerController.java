package com.nolan.learn.springbootsecurityjwt2.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author zhun.huang 2019-03-25
 */
@RestController()
@RequestMapping("/manager")
public class ManagerController {

    @RequestMapping()
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public String manager() {
        return "普通管理员才能看到的私密信息";
    }
}
