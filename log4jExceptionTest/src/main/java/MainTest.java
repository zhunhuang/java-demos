import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * description:
 *
 * @author zhunhuang, 2020/7/3
 */
public class MainTest {
    static Logger LOGGER = LoggerFactory.getLogger(MockService.class);

    public static LongAdder withReflectionCount = new LongAdder();
    public static LongAdder withReflectionCountExclude = new LongAdder();
    public static LongAdder noReflectionCount = new LongAdder();
    public static LongAdder apacheUtilCount = new LongAdder();
    public static LongAdder noExceptionCount = new LongAdder();


    public static void main(String[] args) {

        startMonitor();
//        printExceptionWithReflection(); // QPS 7600
        printExceptionWithReflectionExclude(); //QPS 65000
//        printExceptionNoReflection();// QPS 65000
//        printExceptionApacheUtil();// QPS 65000
        printNoException();//QPS 99000
    }

    private static void startMonitor() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        long start = System.currentTimeMillis();
        scheduledExecutorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("withReflectionCount QPS: " + (withReflectionCount.longValue() * 1000 / (System.currentTimeMillis() - start)));
                        System.out.println("withReflectionCountExclude QPS: " + (withReflectionCountExclude.longValue() * 1000 / (System.currentTimeMillis() - start)));
                        System.out.println("noReflectionCount QPS: " + (noReflectionCount.longValue() * 1000 / (System.currentTimeMillis() - start)));
                        System.out.println("apacheUtilCount QPS: " + (apacheUtilCount.longValue() * 1000 / (System.currentTimeMillis() - start)));
                        System.out.println("noExceptionCount QPS: " + (noExceptionCount.longValue() * 1000 / (System.currentTimeMillis() - start)));
                    }
                },1000,1000, TimeUnit.MILLISECONDS);
    }

    public static void printExceptionWithReflection() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000000; j++) {
                    MockService.HelloService.sayHelloWithReflection("a");
                    withReflectionCount.increment();
                }
            });
        }
    }

    public static void printExceptionWithReflectionExclude() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000000; j++) {
                    MockService.HelloService.sayHelloWithReflectionExclude("a");
                    withReflectionCountExclude.increment();
                }
            });
        }
    }

    public static void printExceptionNoReflection() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000000; j++) {
                    MockService.HelloService.sayHelloNoReflection("a");
                    noReflectionCount.increment();
                }
            });
        }
    }

    public static void printExceptionApacheUtil() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000000; j++) {
                    MockService.HelloService.sayHelloApacheUtil("a");
                    apacheUtilCount.increment();
                }
            });
        }
    }


    public static void printNoException() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000000; j++) {
                    MockService.HelloService.sayHelloPrintNoException("a");
                    noExceptionCount.increment();
                }
            });
        }
    }
}
