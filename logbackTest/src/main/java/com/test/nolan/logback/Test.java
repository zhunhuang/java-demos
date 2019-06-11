package com.test.nolan.logback;

import com.test.nolan.logback.printest.loggername.LoggerNamePrint;
import com.test.nolan.logback.printest.mainfilelog.MainFileLog;
import com.test.nolan.logback.printest.stdoutprint.StdOutTest;
import com.test.nolan.logback.stresstest.AsynLoggerPrintTask;
import com.test.nolan.logback.stresstest.SynLoggerPrintTask;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午2:36
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        stressTest();
//        TestAppender();
    }

    public static void TestAppender() {
        LoggerNamePrint.say();
        StdOutTest.say();
        MainFileLog.say();
    }

    public static void stressTest() {
        AsynLoggerPrintTask.doTask();
        SynLoggerPrintTask.doTask();
    }
}
