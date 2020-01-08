import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.RejectedExecutionException;

import static java.lang.Math.min;

/**
 * @author: zhun.huang
 * @create: 2018-05-14 下午2:46
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Main {

    private static RateLimiter rateLimiter;
    private static RateLimiter rateLimiter2;
    private static MyRateLimiter rateLimiter3;

    static {
        rateLimiter = RateLimiter.create(100);
        rateLimiter2 = RateLimiter.create(10);
        rateLimiter3 = new MyRateLimiter(2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            rateLimitBlockedMethod();
        }
        for (int i = 0; i < 30; i++) {
            rateLimitUnBlockedMethod();
        }
    }

    public static void rateLimitBlockedMethod() {
        rateLimiter3.acquire(1);
        System.out.println("rateLimitBlockedMethod请求一次");
    }

    public static void rateLimitUnBlockedMethod() {
        if (!rateLimiter2.tryAcquire(1)) {
            throw new RejectedExecutionException("方法限流,当前超过" + rateLimiter.getRate() + "qps");
        }
        System.out.println("rateLimitUnBlockedMethod请求一次");
    }

    public static class MyRateLimiter {

        // 每多久发一次令牌: qps=5时,则为200ms
        double stableIntervalMills;

        // 当前桶里面存了多少令牌
        int storedPermits;

        // 下一次可以发令牌的时间
        long nextFreeTicketMills;

        // 桶的容量
        long maxPermits;

        public MyRateLimiter(int qps) {
            stableIntervalMills = 1000d / qps;
            nextFreeTicketMills = System.currentTimeMillis();
            maxPermits = qps * 5;
        }

        public void acquire(int num) {
            long times = System.currentTimeMillis();
            long waitTime = 0;
            synchronized (this) {
                reSync(times);
                if (num <= storedPermits) {
                    storedPermits = storedPermits - num;
                    return;
                } else {
                    waitTime = (long) ((num - storedPermits) * stableIntervalMills);
                    this.nextFreeTicketMills = times + waitTime;
                    this.storedPermits = 0;
                }
            }
            try {
                if (waitTime > 0) {
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(storedPermits);
        }

        private void reSync(long nowMicros) {
            if (nowMicros > nextFreeTicketMills) {
                double newPermits = (nowMicros - nextFreeTicketMills) / stableIntervalMills;
                storedPermits = (int) min(maxPermits, storedPermits + newPermits);
                nextFreeTicketMills = nowMicros;
            }
        }
    }
}
