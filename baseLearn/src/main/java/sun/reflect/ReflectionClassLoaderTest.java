package sun.reflect;

import org.apache.logging.log4j.util.LoaderUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * description:
 * -Dsun.reflect.noInflation=true
 *
 * @author zhunhuang, 2020/7/2
 */
public class ReflectionClassLoaderTest {

    public static LongAdder count = new LongAdder();
    public static LongAdder time = new LongAdder();


    public static void main(String[] args) throws Exception {

        Method sayHelloMethod = Hello.class.getMethod("sayHello");
        Hello hello = new Hello();
        Hello hello2 = new Hello();
        sayHelloMethod.invoke(hello);
        sayHelloMethod.invoke(hello2);
        long startTIme = System.currentTimeMillis();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("平均耗时：" + (time.longValue() / count.longValue()));
                System.out.println("次数：" + count.longValue());
                System.out.println("QPS：" + count.longValue()/ ((System.currentTimeMillis() - startTIme)/1000));
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);

        test(sayHelloMethod);

        Thread.sleep(100000);

    }

    public static void test(Method sayHelloMethod) throws IllegalAccessException, NoSuchFieldException {
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        Field methodAccessor = sayHelloMethod.getClass().getDeclaredField("methodAccessor");
        methodAccessor.setAccessible(true);
        Object sayHelloMethodAccessor1 = methodAccessor.get(sayHelloMethod);

        for (int i = 0; i < 10000000; i++) {
            executorService.submit(() -> {
                try {
                    sayHelloMethod.invoke(new Hello());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


                long start = System.currentTimeMillis();
                try {
                    Class clazz = ReflectionClassLoaderTest.class.getClassLoader().loadClass(sayHelloMethodAccessor1.getClass().getName());
                    if (clazz != null) {
                        return;
                    }
                } catch (final Throwable ignore) {
                    // Ignore exception.
                }

                try {
                    Class clazz = LoaderUtil.loadClass(sayHelloMethodAccessor1.getClass().getName());
                    if (clazz != null) {
                        return;
                    }
                } catch (final ClassNotFoundException | NoClassDefFoundError | SecurityException exx) {
                    // Ignore exception.
                }

                try {
                    Class clazz = ReflectionClassLoaderTest.class.getClassLoader().loadClass(sayHelloMethodAccessor1.getClass().getName());
                    if (clazz != null) {
                        return;
                    }
                } catch (final Throwable ignore) {
                    // Ignore exception.
                }

                count.increment();
                long elapsed = System.currentTimeMillis() - start;
                time.add(elapsed);
                if (elapsed > 50) {
                    System.out.println(">50ms:" +elapsed);
                }
            });
        }
    }

    public static class Hello {

        public Object sayHello() {
            Object a = new Integer(123);
            return a;
        }

    }
}
