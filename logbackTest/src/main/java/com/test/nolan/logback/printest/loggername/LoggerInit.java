package com.test.nolan.logback.printest.loggername;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午3:03
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class LoggerInit {
    public static final Logger REDIS_LOG = LoggerFactory.getLogger("REDIS_LOG");
    public static final Logger DUBBO_ACCESS_LOG = LoggerFactory.getLogger("DUBBO_ACCESS_LOG");
    public static final Logger PERSONAL_VITAL_LOG = LoggerFactory.getLogger("PERSONAL_VITAL_LOG");
    public static final Logger ASYN_LOG = LoggerFactory.getLogger("ASYN_LOG");
}
