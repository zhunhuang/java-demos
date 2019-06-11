package com.test.nolan.statemachine.common.util;

import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-15 上午11:39
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class OrderGenerator {

    public static String generatOrderNo(){
        Date date = new Date();
        int year = date.getYear();
        long orderNo = date.getTime() % 1000000000;
        return String.valueOf(year).concat(String.valueOf(orderNo));
    }
}
