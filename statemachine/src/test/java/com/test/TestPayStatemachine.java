package com.test;

import com.test.nolan.statemachine.client.service.UserMockService;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午10:23
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class TestPayStatemachine {
    private static final Logger logger = LoggerFactory.getLogger("=========");


    public static void main(String[] args) {
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring/root-context.xml");
        logger.info("================================start program================================");
        UserMockService userMockService = (UserMockService)ct.getBean("userMockService");
        HotelOrderBean hotelOrderBean = userMockService.mockCreateOrder("nolan", "ht_rj_123456", new Date(), 100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("sleep exception", e);
        }
        userMockService.mockPay(hotelOrderBean.getOrderNo(), 100);

    }
}
