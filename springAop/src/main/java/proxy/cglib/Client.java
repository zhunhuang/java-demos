package proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * description:
 *
 * @author zhun.huang 2019-02-24
 */
public class Client {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnglandHelloService.class);
        enhancer.setCallback(new LogInterceptor());
        EnglandHelloService proxy = (EnglandHelloService) enhancer.create();

        proxy.sayHello();
    }
}
