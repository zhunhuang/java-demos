package myaop.aop;

import java.lang.reflect.Method;

public interface AopInterceptor {

    Object doAround(Object proxy, Method method, Object[] args);
}
