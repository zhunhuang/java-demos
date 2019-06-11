package provider;

import API.HelloService;
import dubbo.RPCFramwork2;

/**
 * @author: zhun.huang
 * @create: 2018-05-02 上午11:29
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Provider {

    public static void main(String[] args) throws InterruptedException {
        HelloService helloService = new HelloServiceImpl();
        try {
            RPCFramwork2.export(helloService,12371);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(100000);
        System.out.println("exit server");
    }
}
