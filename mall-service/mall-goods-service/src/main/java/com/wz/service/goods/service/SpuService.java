package com.wz.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.goods.model.Product;
import com.wz.api.goods.model.Spu;

public interface SpuService extends IService<Spu> {

    /**
     * 产品保存
     * @param product
     */
    void saveProduct(Product product);

}
