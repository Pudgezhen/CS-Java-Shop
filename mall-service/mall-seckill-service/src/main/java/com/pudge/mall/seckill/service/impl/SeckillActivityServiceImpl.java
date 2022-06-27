package com.pudge.mall.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.mall.seckill.mapper.SeckillActivityMapper;
import com.pudge.mall.seckill.service.SeckillActivityService;
import com.wz.api.seckill.model.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillActivityServiceImpl extends ServiceImpl<SeckillActivityMapper, SeckillActivity> implements SeckillActivityService {

    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Override
    public List<SeckillActivity> validActivity() {
        return seckillActivityMapper.validActivity();
    }
}
