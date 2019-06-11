package com.test.nolan.logback.stresstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午5:51
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class SynLoggerPrintTask {

    private static Logger logger = LoggerFactory.getLogger(SynLoggerPrintTask.class);

    public static void doTask() {
        long beginTime = System.currentTimeMillis();
        String  something = "嗯, 一次要打印多一点,================================================ 就这样吧";
        for (int i = 0; i < 10000; i++) {
            logger.info("这是第 [{}] 次 打印同步日志..... {}, {}, {}", i, something, something, something);
        }
        System.out.println("同步日志打印10000 次耗时: " + (System.currentTimeMillis() - beginTime) + " ms");
    }
}
