package proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * description:
 *
 * @author zhun.huang 2019-02-24
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy: before invoke");
        Object result;
        try {
            // 继承的方式，如果调用method，则又会被拦截
            // 如果调用methodProxy.invoke()，则仍然是调用被代理的方法，属于递归调用
            // 因此，唯一正确的方法是，methodProxy.invokeSuper(), 这里的MethodProxy是cglib的实现，底层是ASM框架，并没有使用反射。
            result = methodProxy.invokeSuper(o,objects);
        } catch (Exception e) {
            System.out.println("cglib proxy: exception, e:" + e.getMessage());
            throw e;
        } finally {
            System.out.println("cglib proxy: after invoke");
        }
        return result;
    }
}
