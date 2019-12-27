package com.mixin.demo.ssm.mvc;

import org.springframework.stereotype.Component;

@Component
public class PayService {
    public void pay() {
        try {
            System.out.println(">>>3.pay业务<<<<<<< ThradName:" + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println(">>>4.pay业务<<<<<<< ThradName:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

