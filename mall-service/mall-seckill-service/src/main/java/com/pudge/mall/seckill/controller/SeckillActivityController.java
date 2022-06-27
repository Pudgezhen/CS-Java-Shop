package com.pudge.mall.seckill.controller;


import com.pudge.mall.seckill.service.SeckillActivityService;
import com.wz.api.seckill.model.SeckillActivity;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "activity")
public class SeckillActivityController {

    @Autowired
    private SeckillActivityService seckillActivityService;


    @GetMapping("")
    public RespResult list(){
        List<SeckillActivity> list = seckillActivityService.validActivity();
        return RespResult.ok(list);
    }

}
