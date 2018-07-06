package com.example.hwanpenndemo1.rabbitmqBean;

import com.alibaba.fastjson.JSON;
import com.example.hwanpenndemo1.config.QueueConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME)
public class MessageConsumer {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitHandler
    public void handler(@Payload MessageEntity messageEntity) {
        logger.info("消费内容：{}", JSON.toJSONString(messageEntity));
    }
}

