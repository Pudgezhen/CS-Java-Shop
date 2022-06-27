package com.pudge.mall.page.feign;

import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-web-page")
public interface SeckillPageFeign {

    /**
     * 生成秒杀静态页
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("page/seckill/goods/{id}")
    RespResult page(@PathVariable("id") String id) throws Exception ;


    /**
     * 删除指定活动的页面
     */
    @DeleteMapping("page/seckill/goods/{acid}")
    RespResult deleByAct(@PathVariable("acid")String acid );
}
