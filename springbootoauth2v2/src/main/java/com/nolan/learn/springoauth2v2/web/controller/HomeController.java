package com.nolan.learn.springoauth2v2.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author zhun.huang 2019-03-25
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/detail")
    public String detail() {
        return "无需登录就能看到的首页详情信息";
    }
}
