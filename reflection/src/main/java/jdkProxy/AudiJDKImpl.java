package jdkProxy;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * description:
 * create: 2018-09-16
 * 这里是从jdk自动生成的class中拷贝出来的代码.怎么生成:
 * @see jdkProxy.ProxyTest#main(String[])
 * 代码运行时, 调用的代理类就是这个类.
 * 被代理的方法只支持 public的, 所以只有
 * Car接口的方法 和 Object的 toString(),hashCode(), equals()方法被重写了.
 * @author zhun.huang
 */
public class AudiJDKImpl extends Proxy implements Car {

    /**
     * 将这个类需要代理的方法作为类静态成员变量存起来.
     */
    private static Method m1;
    private static Method m2;
    private static Method m3;
    private static Method m0;
    /**
     * 在Proxy类中, 保存这实际被调用对象的引用. h
     */

    /**
     * 需要借助接口来初始化被代理的方法.
     * 这就是jdk代理的类为啥必须是实现接口的原因. 不然怎么找这个方法呢.
     */
    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m3 = Class.forName("jdkProxy.Car").getMethod("drive", Class.forName("java.lang.String"), Class.forName("java.lang.String"));
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }

    public AudiJDKImpl(InvocationHandler var1) /* TODO 这个是生成的代码有的, 但这里会编译不通过, 注释掉, 原因待查 throws */ {
        super(var1);
    }

    /**
     * 代理的方法都是这个样子. 实现了对应的接口, 但方法内容就是调用实际应该调用的方法.
     */
    public final boolean equals(Object var1) /* throws */ {
        try {
            return ((Boolean) super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() /* throws */ {
        try {
            return (String) super.h.invoke(this, m2, (Object[]) null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void drive(String var1, String var2) /* throws */{
        try {
            /* h 就是代理类即CarHandler.

            */
            super.h.invoke(this, m3, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    @Override
    public final int hashCode() /*throws*/ {
        try {
            return ((Integer) super.h.invoke(this, m0, (Object[]) null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

}
