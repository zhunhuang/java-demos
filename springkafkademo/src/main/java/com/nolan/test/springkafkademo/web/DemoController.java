package com.nolan.test.springkafkademo.web;

import com.nolan.test.springkafkademo.service.ProduceMqService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author zhun.huang 2019-01-29
 */
@Controller
public class DemoController {

    @Resource
    ProduceMqService produceMqService;

    @RequestMapping(value="/home")
    public String home(@RequestBody String content){
        produceMqService.sendJson("topic.java.kafka.spring.demo",content);
        return "success";
    }
}
