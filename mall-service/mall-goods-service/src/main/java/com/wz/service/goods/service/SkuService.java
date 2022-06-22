package com.wz.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.cart.model.Cart;
import com.wz.api.goods.model.Sku;

import java.util.List;

public interface SkuService extends IService<Sku> {

    /**
     * 根据推广产品分类id查询指定分类下的产品列表
     * @param id
     * @return
     */
    List<Sku> typeSkuItems(Integer id);

    /**
     * 根据推广产品分类id删除指定分类下的产品列表
     * @param id
     * @return
     */
    void deleteTypeSkuItems(Integer id);
    /**
     * 根据推广产品分类id删除指定分类下的产品列表
     * @param id
     * @return
     */
    List<Sku> updateTypeSkuItems(Integer id);

    void dcount(List<Cart> carts);

}
