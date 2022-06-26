package com.pudge.order.config;

import com.wz.mall.util.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class SecurityConfig {


    @Value("${payconfig.aes.skey}")
    private String skey;
    @Value("${payconfig.aes.salt}")
    private String salt;


    /**
     * 加密解密工具类
     */
    @Bean
    public Signature signature(){
        return new Signature(skey,salt);
    }
}
