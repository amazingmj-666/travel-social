package com.wmj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wmj.design.travelsocial.dao")
public class TravelSocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelSocialApplication.class, args);
        System.out.println("旅游社交启动成功");
    }
}