package com.nolan.learn.springbootsecurity.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author zhun.huang 2019-03-22
 */
@RestController()
@RequestMapping("/home")
public class HomePageController {

    @RequestMapping()
    public String home() {
        return "home";
    }
}
