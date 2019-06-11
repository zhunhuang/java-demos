package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: zhun.huang
 * @create: 2018-05-25 上午10:29
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class CarHandler implements InvocationHandler {

    private Car car;

    public CarHandler(Car car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里的proxy就是 JDK自动生成的类的实例.
        // 如果这里调用proxy的drive方法, 就会出现循环调用,
        // 因为proxy的drive方法就是调用这个invoke方法啊.
        System.err.println("before");
        // 这里的car对象才是真正的被代理对象.即Audi.class类的实例.
        // 关于反射调用的性能分析在{reflectionEfficiency}中已经详细说明了.
        Object invokeResult = method.invoke(car, args);
        System.err.println("after");
        return invokeResult;
    }

}
