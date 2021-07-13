package com.example.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 简单消费者
 */
@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics={"topic1"})
    public void onMessage(ConsumerRecord<?,?> record){
        LOGGER.info("简单消费，消费了{}的第{}区中的{}",record.topic(),record.partition(),record.value());
    }
}
