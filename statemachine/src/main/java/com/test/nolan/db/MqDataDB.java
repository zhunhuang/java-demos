package com.test.nolan.db;

import com.test.nolan.statemachine.pay.bean.Message;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午4:40
 * @email: nolan.zhun@gmail.com
 * @description: 模拟消息db
 */
@Component
public class MqDataDB {

    /**
     * key 为消息主题, value 为消息list
     */
    private Map<String, Queue<Message>> mqDataMap = new HashMap<>();

    /**
     * 出队列
     */
    public Message pullOneMessage(String subject) {
        Queue<Message> messages = mqDataMap.get(subject);
        if (messages == null) {
            return null;
        }
        return messages.poll();
    }

    /**
     * 入队列
     */
    public void sendMessage(Message message) {
        Queue<Message> messages = mqDataMap.get(message.getSubject());
        if (CollectionUtils.isEmpty(messages)) {
            messages = new LinkedList<>();
            messages.add(message);
        } else {
            messages.add(message);
        }
        mqDataMap.put(message.getSubject(), messages);
    }
}
