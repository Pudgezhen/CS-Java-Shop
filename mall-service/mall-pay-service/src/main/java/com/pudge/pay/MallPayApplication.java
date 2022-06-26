package com.pudge.pay;


import com.github.wxpay.sdk.WXPay;
import com.pudge.pay.config.WeixinPayConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.pudge.pay.mapper"})
public class MallPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class,args);
    }

    @Bean
    public WXPay wxPay(WeixinPayConfig weixinPayConfig) throws Exception {
        return new WXPay(weixinPayConfig);
    }

}
