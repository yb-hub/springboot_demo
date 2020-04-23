package com.yb.demo.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author yb
 * @description 消费者监听器
 * @data 2020/4/23
 */
@Component
public class ConsumerListener {
    @KafkaListener(topics = "topicB")
    public String consumer(String message){
        System.out.println("consumer:"+message);
        return message;
    }
}
