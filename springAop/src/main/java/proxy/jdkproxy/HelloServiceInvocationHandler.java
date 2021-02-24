package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description: 通用的代理, 想做一些事情
 *
 * @author zhun.huang 2019-02-24
 */
public class HelloServiceInvocationHandler implements InvocationHandler {


    private HelloService target;

    HelloServiceInvocationHandler(HelloService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy: before invoke HelloService");
        Object result;
        try {
            result = method.invoke(target,args);
        } catch (Exception e) {
            System.out.println("jdk proxy: invoke HelloService failed, e:" +e.getMessage());
            throw e;
        } finally {
            System.out.println("jdk proxy: after invoke HelloService");
        }
        return result;
    }
}
