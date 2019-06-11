package com.test.nolan.logback.printest.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @author: zhun.huang
 * @create: 2018-01-10 下午1:30
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class LoggerFilter {

    private static final String USER_NAME_STRING = "userName";

    private static InheritableThreadLocal<String> USER_NAME = new InheritableThreadLocal<>();

    public static void setUserName(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            USER_NAME.set(userName);
            MDC.put(USER_NAME_STRING, userName);
        }
    }

    public static void releaseAll() {
        USER_NAME.remove();
        MDC.clear();
    }
}
