package com.mixin.demo.ssm.queue;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyDefaultSender {
    private final static Logger logger = LoggerFactory.getLogger(MyDefaultSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        //(队列,消息内容)
        System.out.println("rabbitTemplate:"+rabbitTemplate);
        logger.info("info log");
        logger.debug("debug log");
        logger.error("error log");
        rabbitTemplate.convertAndSend("myDefaultQueue","this is a message");
    }
}
