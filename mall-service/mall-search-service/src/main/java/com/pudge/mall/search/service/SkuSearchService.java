package com.pudge.mall.search.service;

import com.wz.api.search.model.SkuES;

import java.util.Map;

public interface SkuSearchService {

    //增加索引
    void add(SkuES skuES);

    //删除索引
    void del(String id);

    Map<String,Object> search(Map<String,Object> searchMap);
}
