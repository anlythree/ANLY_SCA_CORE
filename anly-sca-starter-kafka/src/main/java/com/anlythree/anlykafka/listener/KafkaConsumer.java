package com.anlythree.anlykafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2020/7/10 9:53 上午
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}")
    public void onMessage(String message){
        // 消费消息
        // 这里为插入数据库代码
        //insertIntoDb(buffer);
        log.info("接收到消息：" + message);
    }

}

