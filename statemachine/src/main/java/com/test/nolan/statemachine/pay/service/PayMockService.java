package com.test.nolan.statemachine.pay.service;

import com.test.nolan.db.OrderDataDB;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午11:44
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class PayMockService {
    private static final Logger logger = LoggerFactory.getLogger(PayFlowContextFactory.class);

    @Resource
    private OrderDataDB orderDataDB;
    @Resource
    private PayPort payPort;

    public boolean mockGoPay(String orderNo, int price) {
        if (StringUtils.isBlank(orderNo) || price<0) {
            logger.error("去支付参数错误!");
            return false;
        }
        HotelOrderBean orderBean = orderDataDB.getData(orderNo);
        if (orderBean == null) {
            logger.error("去支付参数错误, 找不到订单号[{}]的订单", orderNo);
            return false;
        }
        PayRecordBean payRecordBean = new PayRecordBean();
        payRecordBean.setStatus(PayStatus.PAY);
        payRecordBean.setPayPrice(price);
        payRecordBean.setMerchantCode(orderBean.getHotelId());
        payRecordBean.setCreateTime(new Date());
        payRecordBean.setUserName(orderBean.getUserName());
        payRecordBean.setOrderNo(orderBean.getOrderNo());
        synchronized (this) {
            return payPort.save(payRecordBean);
        }
    }
}
