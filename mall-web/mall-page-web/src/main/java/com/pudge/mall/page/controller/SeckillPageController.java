package com.pudge.mall.page.controller;

import com.pudge.mall.page.service.SeckillPageService;
import com.wz.api.seckill.feign.SeckillGoodsFeign;
import com.wz.api.seckill.model.SeckillGoods;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
public class SeckillPageController {


    @Autowired
    private SeckillPageService seckillPageService;

    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;

    /**
     * 删除指定活动的页面
     */
    @DeleteMapping("/seckill/goods/{acid}")
    public RespResult deleByAct(@PathVariable("acid")String acid ){
        // 1. 查询当前活动ID对应的商品列表数据
        RespResult<List<SeckillGoods>> resp = seckillGoodsFeign.actGoods(acid);
        List<SeckillGoods> goodsList = resp.getData();
        // 2. 根据列表数据删除页面
        if (goodsList != null){

            for (SeckillGoods seckillGoods : goodsList) {
                seckillPageService.delete(seckillGoods.getId());
            }
        }
        return RespResult.ok();
    }

    /**
        生成秒杀商品详情页
     */
    @GetMapping("/seckill/goods/{id}")
    public RespResult page(@PathVariable("id") String id) throws Exception {
        seckillPageService.html(id);
        return RespResult.ok();
    }

}
