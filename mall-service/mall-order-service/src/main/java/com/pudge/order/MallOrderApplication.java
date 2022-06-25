package com.pudge.order;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = {"com.pudge.order.mapper"})
@EnableFeignClients(basePackages = {"com.wz.api.cart.feign","com.wz.api.goods.feign"})
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class MallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class,args);
    }
}
