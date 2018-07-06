package com.example.hwanpenndemo1.rabbitmqBean;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageEntity implements Serializable {
    /**
     * 消息内容
     */
    private String content;

    public MessageEntity(String content) {
        this.content = content;
    }
}
