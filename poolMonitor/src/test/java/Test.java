import definition.PoolResourceType;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import register.ManualRegister;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    static ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(
            3,
            3,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            new BasicThreadFactory.Builder().namingPattern("threadPoolExecutor1-pool-%d").build(),
            (r, executor) -> LOGGER.error("reject, ignore task,ThreadPoolExecutor: [{}]",((BasicThreadFactory)executor.getThreadFactory()).getNamingPattern()));

    static ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(
            3,
            5,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            new BasicThreadFactory.Builder().namingPattern("threadPoolExecutor2-pool-%d").build(),
            (r, executor) -> LOGGER.error("reject, ignore task,ThreadPoolExecutor: [{}]",((BasicThreadFactory)executor.getThreadFactory()).getNamingPattern()));

    static ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(
            3,
            20,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            new BasicThreadFactory.Builder().namingPattern("threadPoolExecutor3-pool-%d").build(),
            (r, executor) -> LOGGER.error("reject, ignore task,ThreadPoolExecutor: [{}]",((BasicThreadFactory)executor.getThreadFactory()).getNamingPattern()));

    static ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(
            10,
            20,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            new BasicThreadFactory.Builder().namingPattern("threadPoolExecutor4-pool-%d").build(),
            (r, executor) -> LOGGER.error("reject, ignore task,ThreadPoolExecutor: [{}]",((BasicThreadFactory)executor.getThreadFactory()).getNamingPattern()));

    static ThreadPoolExecutor threadPoolExecutor5 = new ThreadPoolExecutor(
            5,
            10,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            new BasicThreadFactory.Builder().namingPattern("threadPoolExecutor5-pool-%d").build(),
            (r, executor) -> LOGGER.error("reject, ignore task,ThreadPoolExecutor: [{}]",((BasicThreadFactory)executor.getThreadFactory()).getNamingPattern()));

    public static void main(String[] args) throws InterruptedException {
        PoolMonitorBootstrap bootstrap = new PoolMonitorBootstrap();
        bootstrap.start();
        ManualRegister.register(PoolResourceType.ThreadPoolExecutor, threadPoolExecutor1);
        ManualRegister.register(PoolResourceType.ThreadPoolExecutor, threadPoolExecutor2);
        ManualRegister.register(PoolResourceType.ThreadPoolExecutor, threadPoolExecutor3);
        ManualRegister.register(PoolResourceType.ThreadPoolExecutor, threadPoolExecutor4);
        ManualRegister.register(PoolResourceType.ThreadPoolExecutor, threadPoolExecutor5);

        try {
            for (int i = 0; i < 10000; i++) {
                threadPoolExecutor1.execute(new TestRun());
                threadPoolExecutor2.execute(new TestRun());
                threadPoolExecutor3.execute(new TestRun());
                threadPoolExecutor4.execute(new TestRun());
                threadPoolExecutor5.execute(new TestRun());
                Thread.sleep(100);
            }
            System.err.println("循环结束");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class TestRun implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("sleep error");
            }
        }
    }

    public static class Test2Run implements Runnable {

        @Override
        public void run() {
            try {
                Random random = new Random();
                int i = random.nextInt() % 200;
                Thread.sleep(300+i);
            } catch (InterruptedException e) {
                System.out.println("sleep error");
            }
        }
    }
}
