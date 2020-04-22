package com.yb.demo.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yb
 * @description kafka生产者
 * @data 2020/4/22
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @PostMapping("/message")
    public String produce(@RequestParam("topicName") String topicName,
            @RequestParam("message") String message){
        kafkaTemplate.send(topicName,message);
        return "success";
    }
}
