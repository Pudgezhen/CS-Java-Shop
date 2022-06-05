package com.wz.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.goods.model.SkuAttribute;

import java.util.List;

public interface SkuAttributeService extends IService<SkuAttribute> {

    /**
    *    根据分类id查询属性集合
     */
    List<SkuAttribute> queryByCategoryId(Integer id);
}
