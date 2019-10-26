package jdkProxy;

import sun.misc.ProxyGenerator;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: zhun.huang
 * @create: 2018-05-25 上午10:30
 * @email: nolan.zhun@gmail.com
 * @description:
 * 测试jdk生成的class文件是什么样子.
 * 这里会继承Proxy类, 实现Car接口(这就是为什么jdk动态代理必须是接口).
 * 调用方法的时候, 实际上是反射调用.
 * 在自动生成的class类中, 已经将要被调用的Method方法 作为成员变量存起来了.
 */
public class ProxyTest {

    public static void maisn(String[] args) throws Exception {
        // 通过这种方式来实例化被代理的对象。
        Car audi = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(), new Class<?>[] {Car.class}, new CarHandler(new Audi()));
        audi.drive("name1", "audi");

        writeProxyClass();
    }

    public static void writeProxyClass(){
        byte[] bs = ProxyGenerator.generateProxyClass("AudiImpl", new Class<?>[] {Car.class});
        try {
            new FileOutputStream(new File("./reflection/AudiImplGeneratedByProxy.class")).write(bs);
        } catch (IOException e) {
            System.out.println("error:: " + e);
        }
    }

    public static void main(String[] args) {
        Car 被代理啦 = (Car)Proxy.newProxyInstance(Car.class.getClassLoader(), new Class[]{Car.class}, new InvocationHandler() {
            Car toProxy = new Audi();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                MyLogAnnotation annotation = method.getAnnotation(MyLogAnnotation.class);
                Object returnValue;
                if (annotation != null) {
                    System.out.println("befor被代理啦");
                    returnValue = method.invoke(toProxy, args);
                    System.out.println("after被代理啦");

                } else {
                    returnValue = method.invoke(toProxy, args);
                }
                return returnValue;
            }
        });
        被代理啦.drive("123","asdad");
        System.out.println("=================");
        被代理啦.drive2("123","asdad");
    }
}
