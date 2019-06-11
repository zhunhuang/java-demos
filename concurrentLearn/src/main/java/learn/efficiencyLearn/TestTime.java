package learn.efficiencyLearn;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhun.huang
 * @create: 2018-01-20 下午12:36
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TestTime {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000000);
        long end = System.currentTimeMillis();
        System.out.println("执行耗时: " + (end - start));
    }

    @Test
    /**
     * 测试单线程和多线程速度
     * 结果:单线程耗时稳定,当计算量较小时,耗时比多线程短
     */
    public void Test1() throws InterruptedException {
        long count = 10000L;
        for (int i = 0; i < 7; i++) {
            concurrency(count);
            serial(count);
            count = count*10;
        }

    }

    private void serial(long count) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        long a = 0;
        for (long i = 0; i < count; i++) {
            a+=5;
        }
        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        System.out.println("串行执行"+count+ "次耗时:" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private void concurrency(long count) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0;
                for (long i = 0; i < count; i++) {
                    a+=5;
                }
            }
        });
        thread.start();
        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        System.out.println("并发执行"+count+ "次耗时:" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
