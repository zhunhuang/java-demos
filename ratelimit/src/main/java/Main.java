import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author: zhun.huang
 * @create: 2018-05-14 下午2:46
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Main {

    private static RateLimiter rateLimiter;
    private static RateLimiter rateLimiter2;

    static {
        rateLimiter = RateLimiter.create(10);
        rateLimiter2 = RateLimiter.create(10);
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
        rateLimiter.acquire();
        System.out.println("rateLimitBlockedMethod请求一次");
    }

    public static void rateLimitUnBlockedMethod() {
        if (!rateLimiter2.tryAcquire(1)) {
            throw new RejectedExecutionException("方法限流,当前超过" + rateLimiter.getRate() + "qps");
        }
        System.out.println("rateLimitUnBlockedMethod请求一次");
    }
}
