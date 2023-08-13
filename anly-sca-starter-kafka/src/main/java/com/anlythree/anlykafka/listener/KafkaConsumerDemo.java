package com.anlythree.anlykafka.listener;

import com.alibaba.fastjson2.JSONObject;
import com.anlythree.anlykafka.dto.Message;
import com.anlythree.common.utils.AnlyTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 监听kafka消息demo
 * @Author: jinhaoxun
 * @Date: 2020/7/10 9:53 上午
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class KafkaConsumerDemo {

    @KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}")
    public void onMessage(String message){
        // 消费消息
        Message<?> messageObj = JSONObject.parseObject(message, Message.class);
        log.info("接收到消息-----------消息体类型:{},消息体发送时间:{},完整kafka消息体内容:{}",
                messageObj.getContentClass(),
                AnlyTimeUtil.stampToTime(messageObj.getSendTime()),
                message);
    }

}

