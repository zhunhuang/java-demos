package test.provider;

import test.API.HelloService;
import rpc.RPCFramwork2;

import java.io.IOException;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 上午11:29
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Provider {

    public static void main(String[] args) throws InterruptedException, IOException {
        HelloService helloService = new HelloServiceImpl();
        try {
            RPCFramwork2.export(helloService,12371);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.in.read();
        System.out.println("exit server");
    }
}
