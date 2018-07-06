package com.example.hwanpenndemo1.utils;

import org.springframework.amqp.support.converter.DefaultClassMapper;

public class RabbitMqFastJsonClassMapper extends DefaultClassMapper {
    public RabbitMqFastJsonClassMapper() {
        super();
        setTrustedPackages("*");
    }
}
