package com.learn.nolan.controller;

import com.learn.nolan.model.UserDO;
import com.learn.nolan.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author zhun.huang 2018-12-17
 */
@Controller
@RequestMapping("")
public class HelloResource {

    @Resource
    private HelloService helloService;

    @RequestMapping("")
    @ResponseBody
    public UserDO hello() {
        return helloService.sayHello();
    }
}
