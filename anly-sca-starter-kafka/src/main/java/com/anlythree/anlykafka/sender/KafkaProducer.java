package com.anlythree.anlykafka.sender;

import com.alibaba.fastjson2.JSONObject;
import com.anlythree.anlykafka.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;


@Slf4j
@Service
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic,String message) {
        // 发送消息
        kafkaTemplate.send(topic, message);
    }

    public void sendMessage(String topic, Message<?> message) {
        // 发送消息
        kafkaTemplate.send(topic, JSONObject.toJSONString(message));
    }
}
