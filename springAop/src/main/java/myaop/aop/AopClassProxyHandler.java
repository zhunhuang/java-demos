package myaop.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description:
 *
 * @author zhunhuang, 2020/8/21
 */
public class AopClassProxyHandler implements InvocationHandler {

    private Object target;

    private AopInterceptor interceptor;

    public AopClassProxyHandler(Object target, AopInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return interceptor.doAround(target, method, args);
    }
}
