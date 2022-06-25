package com.pudge.pay;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.pudge.pay.mapper"})
public class MallPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class,args);
    }
}
