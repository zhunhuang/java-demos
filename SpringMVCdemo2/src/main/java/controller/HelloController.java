package controller;

import http.JacksonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.HelloService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhun.huang
 * @create: 2018-04-03 下午9:27
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@RestController
@RequestMapping("")
public class HelloController {

    @Resource
    HelloService helloService;

    @Resource
    HttpServletRequest request;

    @RequestMapping(value = "", produces = "text/html; charset=utf-8")
    public String hello() {
        return helloService.sayHello();
    }

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello() {
        request.getSession(true);
        return JacksonUtil.serialize(new User());
    }

    public static class User {
        String name = "nolan";
        int age = 17;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
