package jdkProxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void main(String[] args) throws Exception {
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
}
