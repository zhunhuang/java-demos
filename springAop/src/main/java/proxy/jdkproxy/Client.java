package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * description:
 *
 * @author zhun.huang 2019-02-24
 */
public class Client {

    public static void main(String[] args) {
        InvocationHandler helloHandler = new HelloServiceInvocationHandler(new ChineseHelloServiceImpl());
        HelloService proxyHelloService = (HelloService) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{HelloService.class}, helloHandler);

        proxyHelloService.sayHello();
    }
}
