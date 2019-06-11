package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zhun.huang
 * @create: 2018-04-01 下午9:32
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Controller
@RequestMapping("")
public class HelloController {

    /**
     * produces 来设置返回的content-type, 结合EncodingFilter 保证编码正确
     * 关于为什么要设置produces才能正确配置编码的原因：
     * https://www.cnblogs.com/kaiblog/p/7565231.html
     *
     */
    @RequestMapping(value = "",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String hello() {
        return "你好， World!";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(String jumpUrl) {
        return "redirect:" + jumpUrl;
    }
}
