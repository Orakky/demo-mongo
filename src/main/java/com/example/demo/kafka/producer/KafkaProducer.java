package com.example.demo.kafka.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单生产者
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;


    @RequestMapping("/producer")
    public void sendMessage(String message){
        kafkaTemplate.send("topic1",message);
        LOGGER.info("kafka生产者生产:{}",message);
    }
}
