package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@MapperScan("com.example.dao")
public class MybatisNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisNewApplication.class, args);
    }

}
