package com.wz.service.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.api.goods.model.Sku;
import com.wz.service.goods.mapper.SkuMapper;
import com.wz.service.goods.service.SkuService;
import org.springframework.stereotype.Service;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {
}
