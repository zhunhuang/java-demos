package com.test.nolan.logback.printest.loggername;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午3:16
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class LoggerNamePrint {

    private static final Logger logger = LoggerFactory.getLogger(LoggerNamePrint.class);

    public static void say(){
        logger.info("开始打印LoggerNamePrint.......");
        LoggerInit.REDIS_LOG.info("REDIS_LOG info 打印到文件.......");
        LoggerInit.DUBBO_ACCESS_LOG.info("DUBBO_ACCESS_LOG info 打印到文件.......");
        LoggerInit.PERSONAL_VITAL_LOG.info("PERSONAL_VITAL_LOG info 打印到文件.......");
        LoggerInit.ASYN_LOG.info("ASYN_LOG info 打印到文件.......");
    }
}
