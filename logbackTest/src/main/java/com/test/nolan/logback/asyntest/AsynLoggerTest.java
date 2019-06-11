package com.test.nolan.logback.asyntest;

import com.test.nolan.logback.printest.loggername.LoggerInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午5:15
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class AsynLoggerTest {

    private static final Logger logger = LoggerFactory.getLogger(AsynLoggerTest.class);

    private static void printASynLog() {
        LoggerInit.ASYN_LOG.info("异步日志打印.....");
    }

    private static void printALog() {
        logger.info("同步日志打印.....");
    }
}
