import com.test.my.ThreadLocalHelper;
import com.test.my.WrapRunnable;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhun.huang
 * @create: 2018-05-01 下午12:20
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class SimpleImplementTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    static {
        ((ThreadPoolExecutor)executor).prestartCoreThread();
    }

    private Runnable printUserNameRunnable = this::printUserNameThreadLocal;
    private Runnable setUserNameRunnable = this::setUserNameThreadLocal;

    @Test
    public void TestNewThreadWrap() throws InterruptedException {

        ThreadLocalHelper.setThreadLocal("userName", "aaa");
        new Thread(new WrapRunnable(printUserNameRunnable)).start();
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);

        new Thread(new WrapRunnable(setUserNameRunnable)).start();
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        printUserNameThreadLocal();
    }

    @Test
    public void TestNewThreadUnWrap() throws InterruptedException {

        ThreadLocalHelper.setThreadLocal("userName", "aaa");
        new Thread(printUserNameRunnable).start();
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);

        new Thread(setUserNameRunnable).start();
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        printUserNameThreadLocal();
    }


    @Test
    public void TestThreadPoolWrap() throws InterruptedException {

        ThreadLocalHelper.setThreadLocal("userName", "aaa");

        WrapRunnable wrapRunnable = new WrapRunnable(printUserNameRunnable);

        executor.execute(wrapRunnable);


        System.out.println("sleep 1000ms");
        Thread.sleep(1000);

        executor.execute(new WrapRunnable(setUserNameRunnable));
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        printUserNameThreadLocal();
    }

    @Test
    public void TestThreadPoolNoWrap() throws InterruptedException {

        ThreadLocalHelper.setThreadLocal("userName", "aaa");
        executor.execute(printUserNameRunnable);
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);

        executor.execute(setUserNameRunnable);
        System.out.println("sleep 1000ms");
        Thread.sleep(1000);
        printUserNameThreadLocal();
    }

    private void printUserNameThreadLocal() {
        System.out.println(Thread.currentThread().getName() + " " + ThreadLocalHelper.getThreadLocal("userName"));
    }

    private void setUserNameThreadLocal() {
        ThreadLocalHelper.setThreadLocal("userName", "bbb");
    }
}
