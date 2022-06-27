package com.pudge.mall.search.mapper;

import com.wz.api.search.model.SeckillGoodsEs;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SeckillGoodsSearchMapper extends ElasticsearchRepository<SeckillGoodsEs,String> {
}
