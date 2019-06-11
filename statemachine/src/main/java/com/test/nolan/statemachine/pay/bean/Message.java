package com.test.nolan.statemachine.pay.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-14 下午4:42
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Message {

    private String messageId;

    private String subject;

    private Map<String, String> extMap = new HashMap<>();

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPropertie(String key, String value) {
        extMap.put(key, value);
    }

    public String getPropertie(String key) {
        return extMap.get(key);
    }

    private String getMap() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : extMap.entrySet()) {
            sb.append(", ").append(entry.getKey()).append("= '").append(entry.getValue()).append("\'");
        }
        return sb.toString();

    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", subject='" + subject + '\'' +
                getMap() +
                '}';
    }
}
