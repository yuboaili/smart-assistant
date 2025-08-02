package org.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 智能助手应用程序主类
 * 应用程序启动入口
 */
@SpringBootApplication(scanBasePackages = "org.example")
@MapperScan("org.example.mapper")
public class SmartAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartAssistantApplication.class, args);
    }
} 