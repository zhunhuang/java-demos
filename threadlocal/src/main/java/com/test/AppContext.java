package com.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created with IntelliJ IDEA. User: shenjia.yang Date: 13-8-20 Time: 下午2:49 Context.
 * https://www.cnblogs.com/zhangjk1993/archive/2017/03/29/6641745.html
 */
public class AppContext {

    private static Logger logger = LoggerFactory.getLogger(AppContext.class);

    private static final String USER_NAME_STRING = "userName";


    private static final String ORDER_NO_STRING = "orderNo";

    //登录用户名
    private static TransmittableThreadLocal<String> USER_NAME = new TransmittableThreadLocal<String>();

    private static InheritableThreadLocal<String> ORDER_NO = new InheritableThreadLocal<String>();

    public static String getUserName() {
        return USER_NAME.get();
    }

    public static String getOrderNO() {
        return ORDER_NO.get();
    }

    public static void setUserName(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            USER_NAME.set(userName);
//            MDC.put(USER_NAME_STRING, userName);
        }
    }

    public static void setOrderNo(String orderNo) {
        if (StringUtils.isNotEmpty(orderNo)) {
            ORDER_NO.set(orderNo);
//            MDC.put(ORDER_NO_STRING, orderNo);
        } else {
        }
    }

    public static void releaseUserName() {
        USER_NAME.remove();
    }

    public static void releaseAll() {
        USER_NAME.remove();
        ORDER_NO.remove();
        MDC.clear();
    }
}