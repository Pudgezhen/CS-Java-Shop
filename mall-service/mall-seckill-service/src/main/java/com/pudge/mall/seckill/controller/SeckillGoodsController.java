package com.pudge.mall.seckill.controller;


import com.pudge.mall.seckill.service.SeckillGoodsService;
import com.wz.api.seckill.model.SeckillGoods;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/seckill/goods")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @GetMapping("/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id") String id){
        SeckillGoods seckillGoods = seckillGoodsService.getById(id);
        return RespResult.ok(seckillGoods);
    }

    /**
     * 根据活动查询秒杀商品集合
     * @param acid
     * @return
     */
    @GetMapping("/act/{acid}")
    RespResult<List<SeckillGoods>> actGoods(@PathVariable("acid") String acid){
        List<SeckillGoods> seckillGoods = seckillGoodsService.actGoods(acid);
        return RespResult.ok(seckillGoods);
    }
}
