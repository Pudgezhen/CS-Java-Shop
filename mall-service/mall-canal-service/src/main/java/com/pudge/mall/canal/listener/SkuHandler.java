package com.pudge.mall.canal.listener;

import com.alibaba.fastjson.JSON;
import com.wz.api.goods.model.Sku;
import com.wz.api.search.feign.SkuSearchFeign;
import com.wz.api.search.model.SkuES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {

    @Autowired
    private SkuSearchFeign skuSearchFeign;

    /**
     * 增加数据监听
     * @param sku
     */
    @Override
    public void insert(Sku sku) {
        if (sku.getStatus().intValue() == 1){
            SkuES skuES = JSON.parseObject(JSON.toJSONString(sku),SkuES.class);
            skuSearchFeign.save(skuES);
        }
    }

    /**
     *
     * @param before
     * @param after
     */
    @Override
    public void update(Sku before, Sku after) {

        if (after.getStatus().intValue() == 2){
            skuSearchFeign.del(after.getId());
        }else{
            System.out.println("索引更新");
            SkuES skuES = JSON.parseObject(JSON.toJSONString(after),SkuES.class);
            skuSearchFeign.save(skuES);
        }
    }

    /**
     *
     * @param sku
     */
    @Override
    public void delete(Sku sku) {
        System.out.println("索引删除");
        skuSearchFeign.del(sku.getId());
    }
}
