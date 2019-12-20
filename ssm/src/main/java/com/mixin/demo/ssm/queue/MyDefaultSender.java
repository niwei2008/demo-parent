package com.mixin.demo.ssm.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyDefaultSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        //(队列,消息内容)
        System.out.println("rabbitTemplate:"+rabbitTemplate);
        rabbitTemplate.convertAndSend("myDefaultQueue","this is a message");
    }
}
