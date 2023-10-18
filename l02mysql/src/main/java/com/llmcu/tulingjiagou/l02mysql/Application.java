package com.llmcu.tulingjiagou.l02mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages  ={"com.llmcu.tulingjiagou.l02mysql.mapper","com.llmcu.tulingjiagou.l02mysql.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
