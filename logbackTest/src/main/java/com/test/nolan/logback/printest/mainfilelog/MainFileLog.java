package com.test.nolan.logback.printest.mainfilelog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午2:34
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class MainFileLog {

    private static final Logger logger = LoggerFactory.getLogger(MainFileLog.class);

    public static void say(){
        logger.trace("trace 打印到文件.......");
        logger.debug("debug 打印到文件.......");
        logger.info("info 打印到文件.......");
        logger.warn("warn 打印到文件.......");
        logger.error("error 打印到文件.......");
    }
}
