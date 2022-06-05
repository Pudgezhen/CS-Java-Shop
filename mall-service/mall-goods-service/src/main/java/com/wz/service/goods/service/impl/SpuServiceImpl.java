package com.wz.service.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.api.goods.model.*;
import com.wz.service.goods.mapper.BrandMapper;
import com.wz.service.goods.mapper.CategoryMapper;
import com.wz.service.goods.mapper.SkuMapper;
import com.wz.service.goods.mapper.SpuMapper;
import com.wz.service.goods.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 产品保存
     * @param product
     */
    @Override
    public void saveProduct(Product product) {
        //1.保存spu
        Spu spu = product.getSpu();
        if (StringUtils.isEmpty(spu.getId())) {
            spu.setIsMarketable(1); //已上架
            spu.setIsDelete(0); //未删除
            spu.setStatus(1); //已通过
            spuMapper.insert(spu);
        }else{
            //修改Spu
            spuMapper.updateById(spu);
            //删除Sku集合
            skuMapper.delete(new QueryWrapper<Sku>().eq("spu_id",spu.getId()));
        }

        //2.保存sku集合
        Date date =new Date();
        Category category = categoryMapper.selectById(spu.getCategoryThreeId());
        Brand brand = brandMapper.selectById(spu.getBrandId());
        List<Sku> skus = product.getSkus();
        for (Sku sku : skus) {
            //Sku 名称
            String name = spu.getName();
            Map<String,String> skuMap = JSON.parseObject(sku.getSkuAttribute(),Map.class);
            for (Map.Entry<String, String> Entry : skuMap.entrySet()) {
                name+="  " + Entry.getValue();
            }
            sku.setName(name);
            //创建时间
            sku.setCreateTime(date);
            //修改时间
            sku.setUpdateTime(date);
            //品牌id
            sku.setBrandId(spu.getBrandId());
            //品牌名字
            sku.setBrandName(brand.getName());
            //分类id
            sku.setCategoryId(spu.getCategoryThreeId());
            //分类名字
            sku.setCategoryName(category.getName());
            //spuid
            sku.setSpuId(spu.getId());
            //状态
            sku.setStatus(1);

            skuMapper.insert(sku);
        }
    }
}
