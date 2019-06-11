package com.nolan.test.springkafkademo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * description:
 *
 * @author zhun.huang 2019-01-29
 */
@Service
public class ProduceMqService {

        private static final Logger logger = LoggerFactory.getLogger(ProduceMqService.class);
        private Gson gson = new GsonBuilder().create();

        @Autowired
        private KafkaTemplate template;

    /**
     * 发送消息方法
     * @param topic
     * @param json
     */
    public void sendJson(String topic, String json) {
            JSONObject jsonObj = JSON.parseObject(json);

            jsonObj.put("topic", topic);
            jsonObj.put("ts", System.currentTimeMillis() + "");

            logger.info("json+++++++++++++++++++++  message = {}", jsonObj.toJSONString());

            ListenableFuture<SendResult<String, String>> future = template.send(topic, jsonObj.toJSONString());
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("msg OK." + result.toString());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("msg send failed: ");
                }
            });
        }
}
