package com.test.nolan.logback.stresstest;

import com.test.nolan.logback.printest.loggername.LoggerInit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午5:48
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class AsynLoggerPrintTask {

    private static ExecutorService executorService
             = Executors.newFixedThreadPool(20);

    public static void doTask() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                long beginTime = System.currentTimeMillis();
                String  something = "嗯, 一次要打印多一点,================================================ 就这样吧";
                for (int i = 0; i < 10000; i++) {
                    LoggerInit.ASYN_LOG.info("这是第 [{}] 次 打印异步日志..... {}, {}, {}", i, something, something, something);
                }
                System.out.println("异步日志打印10000 次耗时: " + (System.currentTimeMillis() - beginTime) + " ms");
            }
        });
    }
}
