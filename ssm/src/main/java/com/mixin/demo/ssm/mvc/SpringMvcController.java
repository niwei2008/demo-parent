package com.mixin.demo.ssm.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

@Controller
public class SpringMvcController {
    @ResponseBody
    @RequestMapping(value = "/")
    public String boot(HttpServletRequest request) {
        return "this is spring-mvc of annotation";
    }

    @RequestMapping(value = "/index")
    public String index() {
        System.out.println("index方法处理");
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login() {
        System.out.println("login方法处理");
        return "login success";
    }

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/asyncPay")
    @ResponseBody
    public Callable<String> asyncPay() {
        System.out.println("asyncPay方法处理");
        System.out.println(">>>1.开始调用pay<<<<<<< ThradName:" + Thread.currentThread().getName());
        //Callable<String>表示返回类型为String
        Callable callable = new Callable<String>() {
            public String call() throws Exception {
                payService.pay();
                return "success";
            }
        };
        System.out.println(">>>2.结束调用pay<<<<<<< ThradName:" + Thread.currentThread().getName());
        return callable;
    }
}

