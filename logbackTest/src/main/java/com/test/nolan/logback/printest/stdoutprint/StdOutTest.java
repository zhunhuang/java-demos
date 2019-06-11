package com.test.nolan.logback.printest.stdoutprint;

import com.test.nolan.logback.printest.filter.LoggerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 上午11:28
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class StdOutTest {

    private static final Logger logger = LoggerFactory.getLogger(StdOutTest.class);

    public static void say() {
        LoggerFilter.setUserName("zhun.huang");
        logger.trace("trace打印到控制台.......");
        logger.debug("debug打印到控制台.......");
        logger.info("info打印到控制台.......");
        logger.warn("warn打印到控制台.......");
        logger.error("error打印到控制台.......");
    }
}
