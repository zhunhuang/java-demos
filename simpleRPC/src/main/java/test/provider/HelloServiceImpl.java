package test.provider;

import test.API.HelloService;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 上午11:28
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        try {
            System.out.println("HelloServiceImpl sayHello processing...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello, remoter!";
    }
}
