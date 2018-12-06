package com.yfy.beem.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WebMain.class);
    }
}
