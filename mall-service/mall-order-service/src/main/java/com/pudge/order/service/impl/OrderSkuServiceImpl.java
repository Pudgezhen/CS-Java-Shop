package com.pudge.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pudge.order.mapper.OrderSkuMapper;
import com.pudge.order.service.OrderSkuService;
import com.wz.order.model.OrderSku;
import org.springframework.stereotype.Service;

@Service
public class OrderSkuServiceImpl extends ServiceImpl<OrderSkuMapper, OrderSku> implements OrderSkuService {
}
