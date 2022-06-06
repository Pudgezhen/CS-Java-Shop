package com.pudge.mall.canal.listener;

import com.wz.api.goods.feign.SkuFeign;
import com.wz.api.goods.model.AdItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable("ad_items")
public class AdItemHandler implements EntryHandler<AdItems> {

    @Autowired
    private SkuFeign skuFeign;
    @Override
    public void insert(AdItems adItems) {
        System.out.println("ad_items 表插入");
        skuFeign.updtypeItems(adItems.getType());
    }

    @Override
    public void update(AdItems before, AdItems after) {
        System.out.println("ad_items 表修改");
        //分类不同，则重新加载之前的缓存
        if(before.getType().intValue()!=after.getType().intValue()){
            //修改缓存
            skuFeign.updtypeItems(before.getType());
        }
        //加载缓存
        skuFeign.updtypeItems(after.getType());
    }

    @Override
    public void delete(AdItems adItems) {
        System.out.println("ad_items 表删除");
        skuFeign.deltypeItems(adItems.getType());
    }
}
