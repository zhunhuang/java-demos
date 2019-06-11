package com.test.nolan.statemachine.client.service;

import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.service.OrderMockService;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import com.test.nolan.statemachine.pay.service.PayMockService;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午11:35
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class UserMockService {
    private static final Logger logger = LoggerFactory.getLogger(UserMockService.class);

    @Resource
    private PayMockService payMockService;
    @Resource
    private OrderMockService orderMockService;

    public HotelOrderBean mockCreateOrder(String userName, String hotelId, Date time, int payPrice) {
        logger.info("用户点击下单, 用户名:{}, 商户Id: {}, 下单时间: {}, 支付价格: {}", userName, hotelId, time, payPrice);
        return orderMockService.mockCreateOrder(userName, hotelId, time, payPrice);
    }

    public void mockPay(String orderNo, int price) {
        logger.info("用户点击去支付了, orderNo: {}, price", orderNo, price);
        payMockService.mockGoPay(orderNo,price);
    }

    public void mockGuarantee(String userName, String hotelId, Date time, int guaranteePrice) {
        logger.info("用户点击担保下单了, 用户名:{}, 商户Id: {}, 下单时间: {}, 担保金额: {}", userName, hotelId, time, guaranteePrice);
        PayRecordBean payRecordBean = new PayRecordBean();
        int orderNo = RandomUtils.nextInt(1000, 2000);
        payRecordBean.setStatus(PayStatus.PAY);
        payRecordBean.setGuaranteePrice(guaranteePrice);
        payRecordBean.setMerchantCode(hotelId);
        payRecordBean.setCreateTime(time);
        payRecordBean.setUserName(userName);
        payRecordBean.setOrderNo("No." + String.valueOf(orderNo));
    }

    public void mockRecieveMessage(String message) {
        logger.info("用户收到短信: {}", message);
    }
}
