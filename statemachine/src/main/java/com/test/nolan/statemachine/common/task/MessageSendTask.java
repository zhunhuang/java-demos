package com.test.nolan.statemachine.common.task;

import com.test.nolan.statemachine.pay.bean.Constant;
import com.test.nolan.statemachine.pay.bean.Message;
import com.test.nolan.db.MqDataDB;
import com.test.nolan.statemachine.common.service.MQMockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午5:17
 * @email: nolan.zhun@gmail.com
 * @description: 模拟消息服务器发送消息至消费端
 */
@Component
@Lazy(value = false)
public class MessageSendTask {
    private static final Logger logger = LoggerFactory.getLogger(MessageSendTask.class);

    private final List<String> SUBJECT_LIST = new ArrayList<>();

    {
        SUBJECT_LIST.add(Constant.ORDER_STATUS_CHANGE_SUBJECT);
        SUBJECT_LIST.add(Constant.BANK_STATUS_SUBJECT);
        SUBJECT_LIST.add(Constant.PAY_STATUS_CHANGE_SUBJECT);
        SUBJECT_LIST.add(Constant.HOTEL_CONFIRM_SUBJECT);
    }

    @Resource
    private MqDataDB mqDataDB;
    @Resource
    private MQMockService mqMockService;

    @Scheduled(cron = "*/20 * * * * *")
    public void mockMessageSend() {
        for (String subject : SUBJECT_LIST) {
            logger.info("服务器发送[{}]消息至消费端...", subject);
            Message message = mqDataDB.pullOneMessage(subject);
            while (message != null) {
                try {
                    if (subject.equals(Constant.BANK_STATUS_SUBJECT)) {
                        mqMockService.mockProduceBankMessage(message);
                    }
                    if (subject.equals(Constant.PAY_STATUS_CHANGE_SUBJECT)) {
                        mqMockService.mockProducePayMessage(message);
                    }
                    if (subject.equals(Constant.HOTEL_CONFIRM_SUBJECT)) {
                        mqMockService.mockProduceHotelMessage(message);
                    }
                } catch (Exception e) {
                    logger.error("消息发送异常, 稍后重试发送",e);
                    // 异常重试
                    mqDataDB.sendMessage(message);
                }
                message = mqDataDB.pullOneMessage(Constant.ORDER_STATUS_CHANGE_SUBJECT);
            }
        }
    }
}
