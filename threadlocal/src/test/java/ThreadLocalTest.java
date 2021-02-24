import com.test.again.TransmittableThreadLocal;
import com.test.again.TtlRunnable;
import com.test.my.ThreadLocalHelper;
import com.test.my.WrapRunnable;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 * create: 2018-07-06
 *
 * @author zhun.huang
 */
public class ThreadLocalTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    static {
        ((ThreadPoolExecutor) executor).prestartCoreThread();
    }

    @Test
    public void TestOneThread() throws InterruptedException {
//        ThreadLocal<String> userName = new ThreadLocal<>();
        ThreadLocal<String> userName = new InheritableThreadLocal<>();
        userName.set("main-thread-set");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1: " + userName.get());
                userName.set("t1-thread-set");
                System.out.println("t1: " + userName.get());
            }
        });
        t1.start();
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        System.out.println("main: " + userName.get());
    }

    @Test
    public void TestOneThread1() throws InterruptedException {
        ThreadLocal<char[]> userName = new InheritableThreadLocal<>();
        userName.set("main-thread-set".toCharArray());
        Thread t1 = new Thread(() -> {
            userName.get()[0] = 't';
            System.out.println("t1: " + new String(userName.get()));
        });
        t1.start();
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        System.out.println("main: " + new String(userName.get()));
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        ThreadLocal<String> userName = new InheritableThreadLocal<>();
        userName.set("main-thread-set");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("executor t1: " + userName.get());
            }
        });
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        System.out.println("main: " + userName.get());
    }

    @Test
    public void testThreadPool1() throws InterruptedException {
        ThreadLocalHelper.setThreadLocal("userName", "main-thread-set");
        // 未代理
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("executor t1: " + ThreadLocalHelper.getThreadLocal("userName"));
            }
        });
        // 代理runnable
        executor.execute(
            new WrapRunnable(new Runnable() {
                @Override
                public void run() {
                    System.out.println("executor with wrapRunnable t1: " + ThreadLocalHelper.getThreadLocal("userName"));
                }
            }));

        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        System.out.println("main: " + ThreadLocalHelper.getThreadLocal("userName"));


        // 检测线程池线程是否被污染
        executor.execute(() -> {
            System.out.println("executor t1: " + ThreadLocalHelper.getThreadLocal("userName"));
        });

        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
    }

    @Test
    public void testThreadPool2() throws InterruptedException {
        TransmittableThreadLocal<String> userName = new TransmittableThreadLocal<>();
        userName.set("main-thread-set");

        // 未代理
        executor.execute(() -> {
            System.out.println("executor t1: " + userName.get());
        });
        // 代理runnable
        executor.execute(TtlRunnable.get(() -> {
                    System.out.println("executor with TtlRunnable t1: " + userName.get());
//                    userName.set("executor-t1-set");
            System.out.println("executor with TtlRunnable t1: " + userName.get());
        }, false,true));

        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        System.out.println("main: " + userName.get());

        // 检测线程池线程是否被污染
        executor.execute(() -> {
            System.out.println("executor t1: " + userName.get());
        });

        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
    }
}
