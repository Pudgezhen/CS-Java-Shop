package com.pudge.mall.search.service.impl;

import com.pudge.mall.search.mapper.SeckillGoodsSearchMapper;
import com.pudge.mall.search.service.SeckillGoodsSearchService;
import com.wz.api.search.model.SeckillGoodsEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillGoodsSearchServiceImpl implements SeckillGoodsSearchService {

    @Autowired
    private SeckillGoodsSearchMapper seckillGoodsSearchMapper;

    @Override
    public void add(SeckillGoodsEs seckillGoodsEs) {
        seckillGoodsSearchMapper.save(seckillGoodsEs);
    }
}
