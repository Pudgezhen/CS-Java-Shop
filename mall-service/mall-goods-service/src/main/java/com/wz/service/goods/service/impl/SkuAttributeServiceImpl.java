package com.wz.service.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.api.goods.model.SkuAttribute;
import com.wz.service.goods.mapper.SkuAttributeMapper;
import com.wz.service.goods.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    /**
     *    根据分类id查询属性集合
     */
    @Override
    public List<SkuAttribute> queryByCategoryId(Integer id) {
        return skuAttributeMapper.queryByCategoryId(id);
    }
}
