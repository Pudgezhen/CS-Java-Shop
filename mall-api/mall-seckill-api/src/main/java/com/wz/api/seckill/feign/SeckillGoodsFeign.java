package com.wz.api.seckill.feign;

import com.wz.api.seckill.model.SeckillGoods;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("mall-seckill")
public interface SeckillGoodsFeign {


    /**
     * 根据ID查询秒杀商品详情
     * @param id
     * @return
     */
    @GetMapping("/seckill/goods/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id") String id);

    /**
     * 根据活动查询秒杀商品集合
     * @param acid
     * @return
     */
    @GetMapping("/seckill/goods/act/{acid}")
    RespResult<List<SeckillGoods>> actGoods(@PathVariable("acid") String acid);
}
