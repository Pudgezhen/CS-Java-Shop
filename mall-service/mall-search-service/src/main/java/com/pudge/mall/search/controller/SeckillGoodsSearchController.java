package com.pudge.mall.search.controller;

import com.pudge.mall.search.service.SeckillGoodsSearchService;
import com.wz.api.search.model.SeckillGoodsEs;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckill/goods")
public class SeckillGoodsSearchController {

    @Autowired
    private SeckillGoodsSearchService seckillGoodsSearchService;


    @PostMapping("/import")
    public RespResult add(@RequestBody SeckillGoodsEs seckillGoodsEs){
        seckillGoodsSearchService.add(seckillGoodsEs);
        return RespResult.ok();
    }
}
