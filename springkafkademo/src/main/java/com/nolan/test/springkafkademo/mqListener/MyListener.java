package com.nolan.test.springkafkademo.mqListener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * description:
 *
 * @author zhun.huang 2019-01-29
 */
@Component
public class MyListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);

    @KafkaListener(topicPattern = "topic.*")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            LOGGER.info("------------------ message =topic：" + topic + ", " + message);
        }
    }
}
