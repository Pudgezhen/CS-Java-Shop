package com.wz.service.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.api.goods.model.AdItems;
import com.wz.api.goods.model.Sku;
import com.wz.service.goods.mapper.AdItemsMapper;
import com.wz.service.goods.mapper.SkuMapper;
import com.wz.service.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "ad-items-skus")
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private AdItemsMapper adItemsMapper;
    @Autowired
    private SkuMapper skuMapper;



    /**
     * 根据推广产品分类id查询指定分类下的产品列表
     * @param id
     * @return
     */
    @Cacheable(key = "#id")
    @Override
    public List<Sku> typeSkuItems(Integer id) {
        System.out.println("查询数据库");
        //1. 根据当前分类下的所有列表信息
        QueryWrapper<AdItems> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",id);
        List<AdItems> adItems = adItemsMapper.selectList(queryWrapper);

        //2. 根据推广列表查询产品列表信息
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
        return skuMapper.selectBatchIds(skuIds);
    }


    /**
     * 根据推广产品分类id删除指定分类下的产品列表
     * @param id
     * @return
     */
    @CacheEvict(key = "#id")
    @Override
    public void deleteTypeSkuItems(Integer id) {
        System.out.println("查询数据库");
        //1. 根据当前分类下的所有列表信息
        QueryWrapper<AdItems> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",id);
        List<AdItems> adItems = adItemsMapper.selectList(queryWrapper);

        //2. 根据推广列表查询产品列表信息
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
        skuMapper.deleteBatchIds(skuIds);
    }

    /**
     * 根据推广产品分类id修改指定分类下的产品列表
     * @param id
     * @return
     */
    @CachePut(key = "#id")
    @Override
    public List<Sku> updateTypeSkuItems(Integer id) {
        System.out.println("查询数据库");
        //1. 根据当前分类下的所有列表信息
        QueryWrapper<AdItems> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",id);
        List<AdItems> adItems = adItemsMapper.selectList(queryWrapper);

        //2. 根据推广列表查询产品列表信息
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
        return skuMapper.selectBatchIds(skuIds);
    }
}
