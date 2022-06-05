package com.wz.service.file;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

@EnableConfigurationProperties(MybatisPlusProperties.class)
public class MallFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFileApplication.class,args);
    }


}
