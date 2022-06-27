package com.pudge.mall.seckill.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.mall.seckill.mapper.SeckillOrderMapper;
import com.pudge.mall.seckill.service.SeckillOrderService;
import com.wz.api.seckill.model.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;


}
