package com.example.hwanpenndemo1.rabbitController;

import com.example.hwanpenndemo1.rabbitmqBean.MessageEntity;
import com.example.hwanpenndemo1.rabbitmqBean.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /**
     * 消息队列 - 消息提供者 注入
     */
    @Autowired
    private MessageProvider messageProvider;

    /**
     * 测试发送消息队列方法
     *
     * @param messageEntity 发送消息实体内容
     * @return
     */
    @RequestMapping(value = "/rabbitmq")
    public String rabbitmq(MessageEntity messageEntity) {
        // 将实体实例写入消息队列
        messageProvider.sendMessage(messageEntity);
        return "Success";
    }
    @RequestMapping(value = "/rabbitmq1")
    public String rabbitmq1(MessageEntity messageEntity) {
        // 将实体实例写入消息队列
//        messageProvider.sendMessage(messageEntity);
        messageProvider.sendMessage(new MessageEntity("Hello from RabbitMQ!"));
//        messageProvider.sendMessage("Hello from RabbitMQ!");
        return "Success1";
    }
}

