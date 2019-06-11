package com.test.nolan.statemachine.common.service;

import com.test.nolan.statemachine.order.enums.OrderStatus;
import com.test.nolan.statemachine.pay.bean.Constant;
import com.test.nolan.statemachine.pay.bean.Message;
import com.test.statemachine.api.ExecutorInfo;
import com.test.nolan.db.MqDataDB;
import com.test.nolan.db.OrderDataDB;
import com.test.nolan.db.PayDataDB;
import com.test.nolan.statemachine.order.bean.HotelOrderBean;
import com.test.nolan.statemachine.order.service.OrderPort;
import com.test.nolan.statemachine.pay.bean.PayRecordBean;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import com.test.nolan.statemachine.pay.service.PayPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午4:40
 * @email: nolan.zhun@gmail.com
 * @description:
 */
@Service
public class MQMockService {

    private static final Logger logger = LoggerFactory.getLogger(MQMockService.class);

    private AtomicInteger messageId = new AtomicInteger();

    @Resource
    private MqDataDB mqDataDB;
    @Resource
    private PayPort payPort;
    @Resource
    private PayDataDB payDataDB;
    @Resource
    private OrderPort orderPort;
    @Resource
    private OrderDataDB orderDataDB;

    /**
     * 发支付状态变更消息
     */
    public void mockPayStatusChangeMessage(String orderNo, PayStatus payStatus) {
        Message message = new Message();
        message.setMessageId(String.valueOf(messageId.incrementAndGet()));
        message.setSubject(Constant.PAY_STATUS_CHANGE_SUBJECT);
        message.setPropertie("orderNo", orderNo);
        message.setPropertie("status", payStatus.name());
        logger.info("发送支付状态变更消息, message: {}", message);
        mqDataDB.sendMessage(message);
    }

    /**
     * 发支付状态变更消息
     */
    public void mockOrderStatusChangeMessage(String orderNo, OrderStatus orderStatus) {
        Message message = new Message();
        message.setMessageId(String.valueOf(messageId.incrementAndGet()));
        message.setSubject(Constant.ORDER_STATUS_CHANGE_SUBJECT);
        message.setPropertie("orderNo", orderNo);
        message.setPropertie("status", orderStatus.name());
        logger.info("发送订单状态变更消息, message: {}", message);
        mqDataDB.sendMessage(message);
    }

    /**
     * mock银行发消息
     */
    public void mockBankMessage(String transactionId, PayStatus payStatus) {
        Message message = new Message();
        message.setMessageId(String.valueOf(messageId.incrementAndGet()));
        message.setSubject(Constant.BANK_STATUS_SUBJECT);
        message.setPropertie("transactionId", transactionId);
        message.setPropertie("status", payStatus.name());
        logger.info("发送订单状态变更消息, message: {}", message);
        mqDataDB.sendMessage(message);
    }

    /**
     * 发支付状态变更消息
     */
    public void mockConfirmHotelMessage(String orderNo, String status) {
        Message message = new Message();
        message.setMessageId(String.valueOf(messageId.incrementAndGet()));
        message.setSubject(Constant.HOTEL_CONFIRM_SUBJECT);
        message.setPropertie("orderNo", orderNo);
        message.setPropertie("status", status);
        logger.info("发送酒店确认房源消息, message: {}", message);
        mqDataDB.sendMessage(message);
    }

    /**
     * 收消息
     */
    public void mockProduceBankMessage(Message message) {
        logger.info("收到银行支付状态变更消息, message: {}", message);
        String transactionId = message.getPropertie("transactionId");
        String status = message.getPropertie("status");
        if (transactionId == null || status == null) {
            logger.error("消息消费失败, transactionId/status is null");
            return;
        }
        PayRecordBean payRecordBean = payDataDB.getDataByTransactionId(transactionId);
        if (payRecordBean == null) {
            logger.info("消息无须消费, 无对应支付记录, transactionId: {}", transactionId);
            return;
        }
        ExecutorInfo.ExecutorRole executorRole = ExecutorInfo.ExecutorRole.SYSTEM;
        payPort.changeOrderStatus(payRecordBean.getOrderNo(), PayStatus.valueOf(status), executorRole);
    }

    public void mockProducePayMessage(Message message) {
        logger.info("收到支付状态变更消息, message: {}", message);
        String orderNo = message.getPropertie("orderNo");
        String status = message.getPropertie("status");
        if (orderNo == null || status == null) {
            logger.error("消息消费失败, orderNo/status is null");
            return;
        }
        HotelOrderBean orderBean = orderDataDB.getData(orderNo);
        if (orderBean == null) {
            logger.info("消息无须消费, 无对应支付记录, transactionId: {}", orderNo);
            return;
        }
        ExecutorInfo.ExecutorRole executorRole = ExecutorInfo.ExecutorRole.SYSTEM;
        if (PayStatus.valueOf(status) == PayStatus.PAY_SUCCESS) {
            orderPort.changeOrderStatus(orderBean.getOrderNo(), OrderStatus.PAY_OK, executorRole);
        }
    }

    public void mockProduceHotelMessage(Message message) {
        logger.info("收到酒店房源消息, message: {}", message);
        String orderNo = message.getPropertie("orderNo");
        String status = message.getPropertie("status");
        if (orderNo == null || status == null) {
            logger.error("消息消费失败, orderNo/status is null");
            return;
        }
        HotelOrderBean orderBean = orderDataDB.getData(orderNo);
        if (orderBean == null) {
            logger.info("消息无须消费, 无对应支付记录, transactionId: {}", orderNo);
            return;
        }
        ExecutorInfo.ExecutorRole executorRole = ExecutorInfo.ExecutorRole.SUPPLIER;
        if ("confirm".equals(status)) {
            orderPort.changeOrderStatus(orderBean.getOrderNo(), OrderStatus.CONFIRM_OK, executorRole);
        } else {
            orderPort.changeOrderStatus(orderBean.getOrderNo(), OrderStatus.APPLY_4_RETURN_PAY, executorRole);
        }
    }

}
