package com.mixin.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;


/**
 * @author Ni Wei
 * @date 2019-12-16
 */
@SpringBootApplication
@EnableEurekaServer
@PropertySource("classpath:/com/mixin/eureka/server/application.properties")
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}