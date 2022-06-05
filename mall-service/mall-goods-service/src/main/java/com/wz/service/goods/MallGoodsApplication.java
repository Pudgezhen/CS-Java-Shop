package com.wz.service.goods;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.wz.service.goods","com.wz.mall"})
@MapperScan(basePackages = {"com.wz.service.goods.mapper"})
@EnableCaching
public class MallGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class,args);
    }

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        return paginationInnerInterceptor;
    }
}
