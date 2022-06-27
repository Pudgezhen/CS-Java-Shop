package com.wz.api.search.feign;

import com.wz.api.search.model.SeckillGoodsEs;
import com.wz.api.search.model.SkuES;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("mall-search")
@RequestMapping("/seckill")
public interface SeckillGoodsSearchFeign {


    @PostMapping("/goods/import")
    RespResult add(@RequestBody SeckillGoodsEs seckillGoodsEs);
}
