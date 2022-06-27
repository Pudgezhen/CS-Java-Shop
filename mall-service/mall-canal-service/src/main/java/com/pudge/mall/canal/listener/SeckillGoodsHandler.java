package com.pudge.mall.canal.listener;


import com.alibaba.fastjson.JSON;
import com.pudge.mall.page.feign.SeckillPageFeign;
import com.wz.api.search.feign.SeckillGoodsSearchFeign;
import com.wz.api.search.model.SeckillGoodsEs;
import com.wz.api.seckill.model.SeckillGoods;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("seckill_goods")
@Component
public class SeckillGoodsHandler implements EntryHandler<SeckillGoods> {

    @Autowired
    private SeckillGoodsSearchFeign seckillGoodsSearchFeign;

    @Autowired
    private SeckillPageFeign seckillPageFeign;

    @SneakyThrows
    @Override
    public void insert(SeckillGoods seckillGoods) {
        //索引导入
        seckillGoodsSearchFeign.add(JSON.parseObject(JSON.toJSONString(seckillGoods), SeckillGoodsEs.class));

        //生成、更新静态页
        seckillPageFeign.page(seckillGoods.getId());
    }

    @SneakyThrows
    @Override
    public void update(SeckillGoods before, SeckillGoods after) {
        //索引导入
        seckillGoodsSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SeckillGoodsEs.class));

        //生成、更新静态页
        seckillPageFeign.page(after.getId());
    }

    @Override
    public void delete(SeckillGoods seckillGoods) {

    }
}
