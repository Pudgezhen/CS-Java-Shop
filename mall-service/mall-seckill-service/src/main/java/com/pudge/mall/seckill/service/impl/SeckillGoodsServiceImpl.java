package com.pudge.mall.seckill.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.mall.seckill.mapper.SeckillGoodsMapper;
import com.pudge.mall.seckill.service.SeckillGoodsService;
import com.wz.api.seckill.model.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;


    @Override
    public List<SeckillGoods> actGoods(String acid) {
        QueryWrapper<SeckillGoods> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("activity_id",acid);

        return seckillGoodsMapper.selectList(queryWrapper);
    }
}
