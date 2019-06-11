package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LoginService;

import javax.annotation.Resource;

/**
 * @author: zhun.huang
 * @create: 2018-03-30 下午5:21
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@RequestMapping("")
@Controller
public class MainController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(String userName, String password){
        return loginService.login(userName, password)? "success" : "403";
    }

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }
}
