package test.consumer;

import test.API.HelloService;
import rpc.RPCFramwork;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 上午11:43
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        HelloService helloService = RPCFramwork.refer(HelloService.class, "127.0.0.1", 12371);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = helloService.sayHello();
                    System.out.println(s);
                }
            }).start();
            Thread.sleep(100);
        }
        Thread.sleep(1000000);
    }
}
