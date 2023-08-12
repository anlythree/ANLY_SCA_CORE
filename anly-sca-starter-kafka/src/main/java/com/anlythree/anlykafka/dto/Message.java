package com.anlythree.anlykafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * anlysca自定义kafka消息体
 * @Author: anlythree
 * @Date: 2023/8/12 21:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> implements Serializable {

    private static final long serialVersionUID = -8823815862487817875L;

    private String id;

    private T content;

    private Long sendTime;

    public Message(String id, T content) {
        this.id = id;
        this.content = content;
        this.sendTime = System.currentTimeMillis();
    }
}
