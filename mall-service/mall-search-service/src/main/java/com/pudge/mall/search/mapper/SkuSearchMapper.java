package com.pudge.mall.search.mapper;


import com.wz.api.search.model.SkuES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuSearchMapper extends ElasticsearchRepository<SkuES,String> {
}
