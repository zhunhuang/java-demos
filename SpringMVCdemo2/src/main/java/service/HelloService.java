package service;

import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhun.huang 2018-12-17
 */
@Service
public class HelloService {

    public String sayHello(){
        return "你好！";
    }
}
